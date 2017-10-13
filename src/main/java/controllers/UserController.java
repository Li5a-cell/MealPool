package controllers;

import api.UserCreateRequest;
import api.UserResource;
import api.UserUpdateRequest;
import dao.GoalDao;
import dao.UserDao;
import dummy.Dummies;
import generated.tables.records.UserRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserDao userDao;
    private GoalDao goalDao;

    public UserController(UserDao userDao, GoalDao goalDao) {
        this.userDao = userDao;
        this.goalDao = goalDao;
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
    public UserResource get() {
        UserRecord user = userDao.get(Dummies.DUMMY_EATER);
        return new UserResource(user);
    }
}
