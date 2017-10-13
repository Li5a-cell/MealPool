package controllers;

import api.ScheduleRequest;
import dao.RecipeDao;
import dao.ScheduleDao;
import dummy.Dummies;
import generated.tables.records.RecipeRecord;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleController {

    private ScheduleDao scheduleDao;
    private RecipeDao recipeDao;

    public ScheduleController(ScheduleDao scheduleDao, RecipeDao recipeDao) {
        this.scheduleDao = scheduleDao;
        this.recipeDao = recipeDao;
    }

    @POST
    public void create(ScheduleRequest scheduleRequest) {
        RecipeRecord recipe = recipeDao.get(scheduleRequest.name);
        if (recipe == null) {
            recipe = recipeDao.insert(scheduleRequest.name, scheduleRequest.chefId, null, scheduleRequest.price, 1, scheduleRequest.photo, null);
        } else if (!(recipe.getPrice().equals(scheduleRequest.price) && recipe.getPhoto().equals(scheduleRequest.photo))) {
            recipe.setPrice(scheduleRequest.price);
            recipe.setPhoto(scheduleRequest.photo);
            recipeDao.update(recipe);
        }

        DateTime time = new DateTime(scheduleRequest.time);
        scheduleDao.insert(scheduleRequest.chefId, recipe.getId(), time, scheduleRequest.pickUp, scheduleRequest.sitDown);
    }

    @Path("/{id}/signup")
    @PUT
    public void signUp(@PathParam("id") Integer id) {
        scheduleDao.schedule(id, Dummies.DUMMY_EATER);
    }

    @Path("/{id}/approve")
    @POST
    public void approve(@PathParam("id") Integer id, boolean approved) {
        scheduleDao.approve(id, approved);
    }

    @Path("/{id}/verify")
    @POST
    public void verify(@PathParam("id") Integer id, boolean verified) {
        scheduleDao.verify(id, verified);
    }
}
