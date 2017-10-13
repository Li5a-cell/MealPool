package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.UserRecord;

public class UserResource {

    @JsonProperty
    public Integer id;

    @JsonProperty
    public String email;

    @JsonProperty
    public String displayName;

    @JsonProperty
    public String zip;

    @JsonProperty
    public Integer favoriteCount;

    @JsonProperty
    public String photo;

    @JsonProperty
    public Integer weeklyEatingGoal;

    @JsonProperty
    public Integer weeklyCookingGoal;

    public UserResource(UserRecord userRecord) {
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
