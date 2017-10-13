package controllers;

import api.UserUpdateRequest;
import dao.UserDao;
import dummy.Dummies;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PUT
    public void update(UserUpdateRequest request) {
        userDao.update(Dummies.DUMMY_EATER, request.email, request.displayName, request.zip, request.weeklyEatingGoal, request.weeklyCookingGoal, request.photo);
    }
}
