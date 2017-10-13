package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.AccountRecord;

public class UserResource {

    @JsonProperty
    public int id;

    @JsonProperty
    public String email;

    @JsonProperty
    public String displayName;

    @JsonProperty
    public String zip;

    @JsonProperty
    public int favoriteCount;

    @JsonProperty
    public String photo;

    @JsonProperty
    public int weeklyEatingGoal;

    @JsonProperty
    public int weeklyCookingGoal;

    public UserResource(AccountRecord userRecord) {
        this.id = userRecord.getId();
        this.email = userRecord.getEmail();
        this.displayName = userRecord.getDisplayname();
        this.zip = userRecord.getZip();
        this.favoriteCount = userRecord.getFavoritecount();
        this.photo = userRecord.getPhoto();
        this.weeklyEatingGoal = userRecord.getWeeklyeatinggoal();
        this.weeklyCookingGoal = userRecord.getWeeklycookinggoal();
    }
}
