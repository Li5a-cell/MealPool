package api;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateRequest {

    public String email;

    public String displayName;

    public String zip;

    public int weeklyEatingGoal;

    public int weeklyCookingGoal;

    public String photo;
}
