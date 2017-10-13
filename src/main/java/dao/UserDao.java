package dao;

import generated.tables.records.AccountRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static generated.Tables.*;

public class UserDao {

    private DSLContext dsl;
    private KeywordDao keywordDao;

    public UserDao(Configuration jooqConfig, KeywordDao keywordDao) {
        this.dsl = DSL.using(jooqConfig);
        this.keywordDao = keywordDao;
    }

    /**
     * Create a user account
     * Used by: chefs and eaters
     * @param email
     * @param displayName
     * @param password
     * @param zip
     * @param weeklyEatingGoal
     * @param weeklyCookingGoal
     * @param photo
     * @param keywords
     * @return
     */
    public int insert(String email, String displayName, String password, String zip, int weeklyEatingGoal, int weeklyCookingGoal, String photo, List<String> keywords) {
        AccountRecord record = dsl.insertInto(ACCOUNT, ACCOUNT.EMAIL, ACCOUNT.DISPLAYNAME, ACCOUNT.PASSWORD, ACCOUNT.ZIP, ACCOUNT.WEEKLYEATINGGOAL, ACCOUNT.WEEKLYCOOKINGGOAL, ACCOUNT.PHOTO)
                .values(email, displayName, password, zip, weeklyEatingGoal, weeklyCookingGoal, photo)
                .returning(ACCOUNT.ID).fetchOne();

        if (keywords != null) {
            for (String keyword : keywords) {
                Integer keywordId = keywordDao.get(keyword);
                if (keywordId == null) {
                    keywordId = keywordDao.insert(keyword);
                }
                dsl.insertInto(USER_KEYWORD, USER_KEYWORD.USERID, USER_KEYWORD.KEYWORDID)
                        .values(record.getId(), keywordId)
                        .execute();
            }
        }

        return record.getId();
    }

    public void update(int userId, String email, String displayName, String zip, Integer weeklyEatingGoal, Integer weeklyCookingGoal, String photo) {
        AccountRecord record = get(userId);
        if (email != null) {
            record.setEmail(email);
        }
        if (displayName != null) {
            record.setDisplayname(displayName);
        }
        if (zip != null) {
            record.setZip(zip);
        }
        if (weeklyEatingGoal != null) {
            record.setWeeklyeatinggoal(weeklyEatingGoal);
        }
        if (weeklyCookingGoal != null) {
            record.setWeeklycookinggoal(weeklyCookingGoal);
        }
        if (photo != null) {
            record.setPhoto(photo);
        }
        record.update();
    }

    public AccountRecord get(int id) {
        AccountRecord record = dsl.selectFrom(ACCOUNT).where(ACCOUNT.ID.eq(id)).fetchOne();
        return record;
    }
}
