package controllers;

import api.*;
import dao.*;
import dummy.Dummies;
import generated.tables.records.GoalRecord;
import generated.tables.records.RecipeRecord;
import generated.tables.records.ScheduleRecord;
import generated.tables.records.UserRecord;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserDao userDao;
    private GoalDao goalDao;
    private RecipeDao recipeDao;
    private ScheduleDao scheduleDao;

    public UserController(UserDao userDao, GoalDao goalDao, RecipeDao recipeDao, ScheduleDao scheduleDao) {
        this.userDao = userDao;
        this.goalDao = goalDao;
        this.recipeDao = recipeDao;
        this.scheduleDao = scheduleDao;
    }

    @POST
    public void create(UserCreateRequest request) {
        userDao.insert(request.email, request.displayName, request.password, request.zip, request.weeklyEatingGoal, request.weeklyCookingGoal, request.photo, null);
    }

    @PUT
    public void update(UserUpdateRequest request) {
        userDao.update(Dummies.DUMMY_EATER, request.email, request.displayName, request.zip, request.weeklyEatingGoal, request.weeklyCookingGoal, request.photo);
        goalDao.update(Dummies.DUMMY_EATER, request.weeklyEatingGoal, request.weeklyCookingGoal);
    }

    @GET
    public AccountResource get() {
        UserRecord user = userDao.get(Dummies.DUMMY_EATER);
        UserResource userResource = new UserResource(user);

        List<RecipeRecord> recipes = recipeDao.list(user.getId());
        List<RecipeResource> recipeResourceList = new ArrayList<>(recipes.size());
        for (RecipeRecord recipe : recipes) {
            recipeResourceList.add(new RecipeResource(user, recipe));
        }

        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        DateTime startTime = new DateTime(calendar.getTimeInMillis()).withTimeAtStartOfDay();
        DateTime endTime = new DateTime(startTime.getMillis()).plusDays(7);

        List<ScheduleRecord> eatingSchedule = scheduleDao.getEatingSchedule(Dummies.DUMMY_EATER, startTime, endTime);
        List<ScheduleResource> eatingScheduleResourceList = new ArrayList<>(eatingSchedule.size());
        for (ScheduleRecord schedule : eatingSchedule) {
            RecipeRecord recipe = recipeDao.get(schedule.getRecipeid());
            UserRecord chef = userDao.get(recipe.getChefid());
            eatingScheduleResourceList.add(new ScheduleResource(chef, recipe, schedule));
        }

        List<ScheduleRecord> cookingSchedule = scheduleDao.getCookingSchedule(Dummies.DUMMY_EATER, startTime, endTime);
        List<ScheduleResource> cookingScheduleResourceList = new ArrayList<>(cookingSchedule.size());
        for (ScheduleRecord schedule : cookingSchedule) {
            RecipeRecord recipe = null;
            for (RecipeRecord recipeRecord : recipes) {
                if (recipeRecord.getId().equals(schedule.getRecipeid())) {
                    recipe = recipeRecord;
                    break;
                }
            }
            cookingScheduleResourceList.add(new ScheduleResource(user, recipe, schedule));
        }

        List<GoalRecord> goals = goalDao.getAllForUser(Dummies.DUMMY_EATER);
        List<GoalResource> goalResourceList = new ArrayList<>(goals.size());
        for (GoalRecord goal : goals) {
            goalResourceList.add(new GoalResource(goal));
        }

        return new AccountResource(userResource, recipeResourceList, cookingScheduleResourceList, eatingScheduleResourceList, goalResourceList);
    }
}
