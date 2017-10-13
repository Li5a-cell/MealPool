package dao;

import generated.tables.records.UserRecord;
import generated.tables.records.GoalRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static generated.Tables.*;

public class GoalDao {

    private static final DateFormat df = new SimpleDateFormat("yyyy-ww");

    private DSLContext dsl;
    private UserDao userDao;

    public GoalDao(Configuration jooqConfig, UserDao userDao) {
        this.dsl = DSL.using(jooqConfig);
        this.userDao = userDao;
    }

    public int insert(int userId) {
        UserRecord user = userDao.get(userId);
        GoalRecord goal = dsl.insertInto(GOAL, GOAL.USERID, GOAL.EATINGGOAL, GOAL.COOKINGGOAL, GOAL.WEEK)
                .values(userId, user.getWeeklyeatinggoal(), user.getWeeklycookinggoal(), df.format(new Date())).returning(GOAL.ID).fetchOne();
        return goal.getId();
    }

    public GoalRecord getThisWeeksGoal(int userId) {
        GoalRecord record = dsl.selectFrom(GOAL).where(GOAL.USERID.eq(userId)).and(GOAL.WEEK.eq(df.format(new Date()))).fetchOne();
        if (record == null) {
            //If the goal hasn't been created yet, create it and then retrieve it again
            insert(userId);
            record = dsl.selectFrom(GOAL).where(GOAL.USERID.eq(userId)).and(GOAL.WEEK.eq(df.format(new Date()))).fetchOne();
        }
        return record;
    }

    public void update(int userId, int weeklyEatingGoal, int weeklyCookingGoal) {
        GoalRecord record = getThisWeeksGoal(userId);
        record.setEatinggoal(weeklyEatingGoal);
        record.setCookinggoal(weeklyCookingGoal);
        record.update();
    }

    public List<GoalRecord> getAllForUser(int userId) {
        List<GoalRecord> records = dsl.selectFrom(GOAL).where(GOAL.USERID.eq(userId)).orderBy(GOAL.WEEK).fetch();
        if (records.isEmpty() || !records.get(records.size() - 1).getWeek().equals(df.format(new Date()))) {
            records.add(getThisWeeksGoal(userId));
        }
        return records;
    }

    public void incrementCookingGoal(int userId) {
        GoalRecord record = getThisWeeksGoal(userId);
        record.setCookingcount(record.getCookingcount() + 1);
        record.update();
    }

    public void incrementEatingGoal(int userId) {
        GoalRecord record = getThisWeeksGoal(userId);
        record.setEatingcount(record.getEatingcount() + 1);
        record.update();
    }
}
