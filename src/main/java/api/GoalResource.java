package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.GoalRecord;

public class GoalResource {

    @JsonProperty
    public String week;

    @JsonProperty
    public Integer eatingGoal;

    @JsonProperty
    public Integer eatingCount;

    @JsonProperty
    public Integer cookingGoal;

    @JsonProperty
    public Integer cookingCount;

    public GoalResource(GoalRecord goal) {
        this.week = goal.getWeek();
        this.eatingGoal = goal.getEatinggoal();
        this.eatingCount = goal.getEatingcount();
        this.cookingGoal = goal.getCookinggoal();
        this.cookingCount = goal.getCookingcount();
    }
}
