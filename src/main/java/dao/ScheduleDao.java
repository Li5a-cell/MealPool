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
     * @param recipeId
     * @param time
     * @param pickUp
     * @param sitDown
     * @return
     */
    public int insert(int recipeId, DateTime time, boolean pickUp, boolean sitDown) {
        java.sql.Timestamp sDate = new java.sql.Timestamp(time.getMillis());
        ScheduleRecord record = dsl.insertInto(SCHEDULE, SCHEDULE.RECIPEID, SCHEDULE.SCHEDULED, SCHEDULE.PICKUP, SCHEDULE.SITDOWN)
                .values(recipeId, sDate, pickUp, sitDown)
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
                .join(RECIPE).on(SCHEDULE.RECIPEID.eq(RECIPE.ID))
                .join(ACCOUNT).on(RECIPE.CHEFID.eq(ACCOUNT.ID))
                .where(RECIPE.CHEFID.ne(userId)).and(ACCOUNT.ZIP.eq(zip)).and(SCHEDULE.SCHEDULED.between(sStartOfDay, sEndOfDay))
                .fetch().into(SCHEDULE);
        return records;
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
}
