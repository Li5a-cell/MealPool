package dao;

import generated.tables.records.ScheduleRecord;
import generated.tables.records.UserScheduleRecord;
import org.joda.time.DateTime;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static generated.Tables.*;

public class ScheduleDao {

    private DSLContext dsl;

    public ScheduleDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    /**
     * Inserts a new schedule entry
     * Used by: chef
     * @param chefId
     * @param recipeId
     * @param time
     * @param pickUp
     * @param sitDown
     * @return
     */
    public int insert(int chefId, int recipeId, DateTime time, boolean pickUp, boolean sitDown) {
        java.sql.Timestamp sDate = new java.sql.Timestamp(time.getMillis());
        ScheduleRecord record = dsl.insertInto(SCHEDULE, SCHEDULE.CHEFID, SCHEDULE.RECIPEID, SCHEDULE.SCHEDULED, SCHEDULE.PICKUP, SCHEDULE.SITDOWN)
                .values(chefId, recipeId, sDate, pickUp, sitDown)
                .returning(SCHEDULE.ID).fetchOne();

        return record.getId();
    }

    /**
     * Find available recipes for a given day
     * Used by: eater
     * @param userId
     * @param zip
     * @param time
     * @return
     */
    public List<ScheduleRecord> find(int userId, String zip, DateTime time) {
        DateTime startOfDay = time.withTimeAtStartOfDay();
        DateTime endOfDay = startOfDay.plusDays(1).minusMillis(1);
        java.sql.Timestamp sStartOfDay = new java.sql.Timestamp(startOfDay.getMillis());
        java.sql.Timestamp sEndOfDay = new java.sql.Timestamp(endOfDay.getMillis());

        List<ScheduleRecord> records = dsl.select()
                .from(SCHEDULE)
                .join(USER).on(SCHEDULE.CHEFID.eq(USER.ID))
                .where(SCHEDULE.CHEFID.ne(userId)).and(USER.ZIP.eq(zip)).and(SCHEDULE.SCHEDULED.between(sStartOfDay, sEndOfDay))
                .fetch().into(SCHEDULE);
        return records;
    }

    /**
     * Sign up to participate
     * Used by: eater
     * @param scheduleId
     * @param userId
     * @return
     */
    public int schedule(int scheduleId, int userId) {
        UserScheduleRecord record = dsl.selectFrom(USER_SCHEDULE)
                .where(USER_SCHEDULE.SCHEDULEID.eq(scheduleId)).and(USER_SCHEDULE.USERID.eq(userId)).fetchOne();

        if (record == null) {
            record = dsl.insertInto(USER_SCHEDULE, USER_SCHEDULE.SCHEDULEID, USER_SCHEDULE.USERID)
                    .values(scheduleId, userId)
                    .returning(USER_SCHEDULE.ID).fetchOne();
        }

        return record.getId();
    }

    /**
     * get all users currently scheduled for a particular recipe
     * Used by: chef
     * @param scheduleId
     * @return
     */
    public List<UserScheduleRecord> getScheduledUsers(int scheduleId) {
        List<UserScheduleRecord> records = dsl.select()
                .from(USER_SCHEDULE)
                .join(SCHEDULE).on(USER_SCHEDULE.SCHEDULEID.eq(SCHEDULE.ID))
                .where(USER_SCHEDULE.SCHEDULEID.eq(scheduleId))
                .fetch().into(USER_SCHEDULE);
        return records;
    }

    /**
     * Approve a user's participation
     * Used by: chef
     * @param id
     * @param approve
     */
    public void approve(int id, boolean approve) {
        dsl.update(USER_SCHEDULE).set(USER_SCHEDULE.APPROVED, approve).where(USER_SCHEDULE.ID.eq(id)).returning().execute();
    }

    /**
     * Verify a user's participation
     * Used by: chef
     * @param id
     * @param verified
     */
    public void verify(int id, boolean verified) {
        dsl.update(USER_SCHEDULE).set(USER_SCHEDULE.VERIFIED, verified).where(USER_SCHEDULE.ID.eq(id)).returning().execute();
    }
}
