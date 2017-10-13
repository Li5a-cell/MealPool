package api;

import org.hibernate.validator.constraints.NotEmpty;

public class UserCreateRequest {

    @NotEmpty
    public String email;

    @NotEmpty
    public String displayName;

    @NotEmpty
    public String password;

    @NotEmpty
    public String zip;

    public int weeklyEatingGoal;

    public int weeklyCookingGoal;

    public String photo;
}
