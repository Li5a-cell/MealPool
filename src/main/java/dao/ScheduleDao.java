package dao;

import generated.tables.records.ScheduleRecord;
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
                .join(USER).on(RECIPE.CHEFID.eq(USER.ID))
                .where(RECIPE.CHEFID.ne(userId)).and(USER.ZIP.eq(zip)).and(SCHEDULE.SCHEDULED.between(sStartOfDay, sEndOfDay))
                .fetch().into(SCHEDULE);
        return records;
    }

    /**
     * Gets a user's cooking schedule for a given week
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ScheduleRecord> getCookingSchedule(int userId, DateTime startTime, DateTime endTime) {
        java.sql.Timestamp sStartTime = new java.sql.Timestamp(startTime.getMillis());
        java.sql.Timestamp sEndTime = new java.sql.Timestamp(endTime.getMillis());
        List<ScheduleRecord> cookingSchedule = dsl.select()
                .from(SCHEDULE)
                .join(RECIPE).on(SCHEDULE.RECIPEID.eq(RECIPE.ID))
                .join(USER).on(RECIPE.CHEFID.eq(USER.ID))
                .where(RECIPE.CHEFID.eq(userId)).and(SCHEDULE.SCHEDULED.between(sStartTime, sEndTime))
                .fetch().into(SCHEDULE);
        return cookingSchedule;
    }

    /**
     * Gets a user's eating schedule for a given week
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ScheduleRecord> getEatingSchedule(int userId, DateTime startTime, DateTime endTime) {
        java.sql.Timestamp sStartTime = new java.sql.Timestamp(startTime.getMillis());
        java.sql.Timestamp sEndTime = new java.sql.Timestamp(endTime.getMillis());
        List<ScheduleRecord> eatingSchedule = dsl.select()
                .from(SCHEDULE)
                .join(USER_SCHEDULE).on(SCHEDULE.ID.eq(USER_SCHEDULE.SCHEDULEID))
                .where(USER_SCHEDULE.USERID.eq(userId)).and(SCHEDULE.SCHEDULED.between(sStartTime, sEndTime))
                .fetch().into(SCHEDULE);
        return eatingSchedule;
    }
}
