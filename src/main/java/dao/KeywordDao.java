package dao;

import generated.tables.records.KeywordRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static generated.Tables.*;

public class KeywordDao {

    private DSLContext dsl;

    public KeywordDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    /**
     * Create a new keyword
     * Used by: system
     * @param name
     * @return
     */
    public int insert(String name) {
        KeywordRecord record = dsl.insertInto(KEYWORD, KEYWORD.NAME).values(name).returning(KEYWORD.ID).fetchOne();
        return record.getId();
    }

    /**
     * Retrieve a keyword by name
     * Used by: system
     * @param name
     * @return
     */
    public Integer get(String name) {
        KeywordRecord record = dsl.selectFrom(KEYWORD).where(KEYWORD.NAME.eq(name)).fetchOne();
        return record == null ? null : record.getId();
    }
}
