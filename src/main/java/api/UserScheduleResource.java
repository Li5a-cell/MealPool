package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.UserRecord;
import generated.tables.records.UserScheduleRecord;

public class UserScheduleResource {

    @JsonProperty
    UserResource eater;

    @JsonProperty
    Boolean approved;

    @JsonProperty
    Boolean verified;

    public UserScheduleResource(UserRecord eater, UserScheduleRecord userSchedule) {
        this.eater = new UserResource(eater);
        this.approved = userSchedule.getApproved();
        this.verified = userSchedule.getVerified();
    }
}
