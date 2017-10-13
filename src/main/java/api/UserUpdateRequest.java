package api;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateRequest {

    @NotEmpty
    public String email;

    @NotEmpty
    public String displayName;

    @NotEmpty
    public String zip;

    public int weeklyEatingGoal;

    public int weeklyCookingGoal;

    public String photo;
}
