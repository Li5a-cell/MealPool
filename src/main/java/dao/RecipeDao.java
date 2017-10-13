package dao;

import generated.tables.records.RecipeRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static generated.Tables.*;

public class RecipeDao {

    private DSLContext dsl;
    private KeywordDao keywordDao;

    public RecipeDao(Configuration jooqConfig, KeywordDao keywordDao) {
        this.dsl = DSL.using(jooqConfig);
        this.keywordDao = keywordDao;
    }

    /**
     * Create a new recipe
     * Used by: chef
     * @param name
     * @param chefId
     * @param description
     * @param price
     * @param servings
     * @param photo
     * @param keywords
     * @return
     */
    public RecipeRecord insert(String name, int chefId, String description, BigDecimal price, int servings, String photo, Set<String> keywords) {
        RecipeRecord record = dsl.insertInto(RECIPE, RECIPE.NAME, RECIPE.CHEFID, RECIPE.DESCRIPTION, RECIPE.PRICE, RECIPE.SERVINGS, RECIPE.PHOTO)
                .values(name, chefId, description, price, servings, photo)
                .returning().fetchOne();

        if (keywords != null) {
            for (String keyword : keywords) {
                Integer keywordId = keywordDao.get(keyword);
                if (keywordId == null) {
                    keywordId = keywordDao.insert(keyword);
                }
                dsl.insertInto(RECIPE_KEYWORD, RECIPE_KEYWORD.RECIPEID, RECIPE_KEYWORD.KEYWORDID)
                        .values(record.getId(), keywordId)
                        .execute();
            }
        }

        return record;
    }

    public RecipeRecord get(String name) {
        RecipeRecord record = dsl.selectFrom(RECIPE).where(RECIPE.NAME.eq(name)).fetchOne();
        return record;
    }

    public void update(RecipeRecord recipeRecord) {
        recipeRecord.update();
    }

    /**
     * Get all of a chef's recipes
     * Used by: chefs
     * @param chefId
     * @return
     */
    public List<RecipeRecord> list(int chefId) {
        List<RecipeRecord> records = dsl.selectFrom(RECIPE).where(RECIPE.CHEFID.eq(chefId)).fetch();
        return records;
    }
}
