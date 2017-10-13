package api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountResource {

    @JsonProperty
    public UserResource userResource;

    @JsonProperty
    public List<RecipeResource> recipes;

    @JsonProperty
    public List<ScheduleResource> cookingEvents;

    @JsonProperty
    public List<ScheduleResource> eatingEvents;

    @JsonProperty
    public List<GoalResource> goals;

    public AccountResource(UserResource userResource, List<RecipeResource> recipes, List<ScheduleResource> cookingEvents, List<ScheduleResource> eatingEvents, List<GoalResource> goals) {
        this.userResource = userResource;
        this.recipes = recipes;
        this.cookingEvents = cookingEvents;
        this.eatingEvents = eatingEvents;
        this.goals = goals;
    }
}
