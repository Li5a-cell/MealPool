package controllers;

import dao.GoalDao;
import dao.UserScheduleDao;
import dummy.Dummies;
import generated.tables.records.GoalRecord;
import generated.tables.records.UserScheduleRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/user/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserScheduleController {

    private UserScheduleDao userScheduleDao;
    private GoalDao goalDao;

    public UserScheduleController(UserScheduleDao userScheduleDao, GoalDao goalDao) {
        this.userScheduleDao = userScheduleDao;
        this.goalDao = goalDao;
    }

    @POST
    public void signUp(Integer scheduleId) {
        userScheduleDao.insert(scheduleId, Dummies.DUMMY_EATER);
    }

    @Path("/{id}/approve")
    @PUT
    public void approve(@PathParam("id") Integer id, boolean approved) {
        userScheduleDao.approve(id, approved);
    }

    @Path("/{id}/verify")
    @PUT
    public void verify(@PathParam("id") Integer id, boolean verified) {
        UserScheduleRecord userSchedule = userScheduleDao.verify(id, verified);
        goalDao.incrementEatingGoal(userSchedule.getUserid());
    }
}
