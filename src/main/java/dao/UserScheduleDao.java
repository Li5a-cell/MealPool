package dao;

import generated.tables.records.UserScheduleRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static generated.Tables.*;

public class UserScheduleDao {

    private DSLContext dsl;

    public UserScheduleDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    /**
     * Sign up to participate
     * Used by: eater
     * @param scheduleId
     * @param userId
     * @return
     */
    public int insert(int scheduleId, int userId) {
        UserScheduleRecord record = dsl.selectFrom(USER_SCHEDULE)
                .where(USER_SCHEDULE.SCHEDULEID.eq(scheduleId)).and(USER_SCHEDULE.USERID.eq(userId)).fetchOne();

        if (record == null) {
            record = dsl.insertInto(USER_SCHEDULE, USER_SCHEDULE.SCHEDULEID, USER_SCHEDULE.USERID)
                    .values(scheduleId, userId)
                    .returning(USER_SCHEDULE.ID).fetchOne();
        }

        return record.getId();
    }

    public UserScheduleRecord get(int id) {
        UserScheduleRecord record = dsl.selectFrom(USER_SCHEDULE).where(USER_SCHEDULE.ID.eq(id)).fetchOne();
        return record;
    }

    /**
     * Approve a user's participation
     * Used by: chef
     * @param id
     * @param approve
     */
    public void approve(int id, boolean approve) {
        UserScheduleRecord record = get(id);
        record.setApproved(approve);
        record.update();
    }

    /**
     * Verify a user's participation
     * Used by: chef
     * @param id
     * @param verified
     */
    public UserScheduleRecord verify(int id, boolean verified) {
        UserScheduleRecord record = get(id);
        record.setVerified(verified);
        record.update();
        return record;
    }
}
