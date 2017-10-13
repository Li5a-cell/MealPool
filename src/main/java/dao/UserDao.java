package dao;

import generated.tables.records.UserRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static generated.Tables.USER;
import static generated.Tables.USER_KEYWORD;

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
        UserRecord record = dsl.insertInto(USER, USER.EMAIL, USER.DISPLAYNAME, USER.PASSWORD, USER.ZIP, USER.WEEKLYEATINGGOAL, USER.WEEKLYCOOKINGGOAL, USER.PHOTO)
                .values(email, displayName, password, zip, weeklyEatingGoal, weeklyCookingGoal, photo)
                .returning(USER.ID).fetchOne();

        for (String keyword : keywords) {
            Integer keywordId = keywordDao.get(keyword);
            if (keywordId == null) {
                keywordId = keywordDao.insert(keyword);
            }
            dsl.insertInto(USER_KEYWORD, USER_KEYWORD.USERID, USER_KEYWORD.KEYWORDID)
                    .values(record.getId(), keywordId)
                    .execute();
        }

        return record.getId();
    }
}
