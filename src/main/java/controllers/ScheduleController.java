package controllers;

import api.ScheduleRequest;
import dao.RecipeDao;
import dao.ScheduleDao;
import generated.tables.records.RecipeRecord;
import org.joda.time.DateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
