package controllers;

import api.ScheduleRequest;
import api.ScheduleResource;
import api.UserScheduleResource;
import dao.GoalDao;
import dao.RecipeDao;
import dao.ScheduleDao;
import dao.UserDao;
import dummy.Dummies;
import generated.tables.records.RecipeRecord;
import generated.tables.records.ScheduleRecord;
import generated.tables.records.UserRecord;
import generated.tables.records.UserScheduleRecord;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Path("/api/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleController {

    private static final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

    private ScheduleDao scheduleDao;
    private RecipeDao recipeDao;
    private UserDao userDao;
    private GoalDao goalDao;

    public ScheduleController(ScheduleDao scheduleDao, RecipeDao recipeDao, UserDao userDao, GoalDao goalDao) {
        this.scheduleDao = scheduleDao;
        this.recipeDao = recipeDao;
        this.userDao = userDao;
    }

    @POST
    public void create(ScheduleRequest scheduleRequest) {
        RecipeRecord recipe = recipeDao.get(Dummies.DUMMY_EATER, scheduleRequest.name);
        if (recipe == null) {
            recipe = recipeDao.insert(scheduleRequest.name, Dummies.DUMMY_CHEF, scheduleRequest.description, scheduleRequest.price, scheduleRequest.servings, scheduleRequest.photo, null);
        } else if (!(recipe.getPrice().equals(scheduleRequest.price) && recipe.getPhoto().equals(scheduleRequest.photo))) {
            recipe.setPrice(scheduleRequest.price);
            recipe.setPhoto(scheduleRequest.photo);
            recipeDao.update(recipe);
        }

        DateTime time = new DateTime(scheduleRequest.time);
        scheduleDao.insert(recipe.getId(), time, scheduleRequest.pickUp, scheduleRequest.sitDown);
        goalDao.incrementCookingGoal(Dummies.DUMMY_CHEF);
    }

    @GET
    public List<ScheduleResource> find(String time) {
        UserRecord user = userDao.get(Dummies.DUMMY_EATER);

        List<ScheduleRecord> scheduleRecords = null;
        try {
            scheduleRecords = scheduleDao.find(user.getId(), user.getZip(), new DateTime(df.parse(time)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<ScheduleResource> scheduleResources = new ArrayList<>(scheduleRecords.size());
        for (ScheduleRecord scheduleRecord : scheduleRecords) {
            RecipeRecord recipe = recipeDao.get(scheduleRecord.getRecipeid());
            UserRecord chef = userDao.get(recipe.getChefid());
            scheduleResources.add(new ScheduleResource(chef, recipe, scheduleRecord));
        }
        return scheduleResources;
    }

    @GET
    @Path("/{id}")
    public List<UserScheduleResource> getUsers(@PathParam("id") Integer id) {
        List<UserScheduleRecord> records = scheduleDao.getScheduledUsers(id);

        List<UserScheduleResource> userScheduleResources = new ArrayList<>(records.size());
        for (UserScheduleRecord record : records) {
            UserRecord eater = userDao.get(record.getUserid());
            userScheduleResources.add(new UserScheduleResource(eater, record));
        }
        return userScheduleResources;
    }
}
