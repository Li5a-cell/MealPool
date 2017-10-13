package controllers;

import api.ScheduleRequest;
import api.UserScheduleRequest;
import api.UserScheduleResource;
import dao.GoalDao;
import dao.UserDao;
import dao.UserScheduleDao;
import dummy.Dummies;
import generated.tables.records.GoalRecord;
import generated.tables.records.UserRecord;
import generated.tables.records.UserScheduleRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/api/user/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserScheduleController {

    private UserScheduleDao userScheduleDao;
    private GoalDao goalDao;
    private UserDao userDao;

    public UserScheduleController(UserScheduleDao userScheduleDao, GoalDao goalDao, UserDao userDao) {
        this.userScheduleDao = userScheduleDao;
        this.goalDao = goalDao;
        this.userDao = userDao;
    }

    @POST
    public void signUp(UserScheduleRequest userScheduleRequest) {
        userScheduleDao.insert(userScheduleRequest.scheduleId, Dummies.DUMMY_EATER);
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

    @GET
    @Path("/{scheduleId}")
    public List<UserScheduleResource> getUsers(@PathParam("scheduleId") Integer scheduleId) {
        List<UserScheduleRecord> records = userScheduleDao.getScheduledUsers(scheduleId);

        List<UserScheduleResource> userScheduleResources = new ArrayList<>(records.size());
        for (UserScheduleRecord record : records) {
            UserRecord eater = userDao.get(record.getUserid());
            userScheduleResources.add(new UserScheduleResource(eater, record));
        }
        return userScheduleResources;
    }
}
