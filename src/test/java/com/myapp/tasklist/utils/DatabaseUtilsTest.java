package com.myapp.tasklist.utils;

import static com.myapp.tasklist.utils.DatabaseUtils.EQUAL;
import static com.myapp.tasklist.utils.DatabaseUtils.and;
import static com.myapp.tasklist.utils.DatabaseUtils.or;
import static com.myapp.tasklist.utils.DatabaseUtils.where;

import org.junit.jupiter.api.Test;

public class DatabaseUtilsTest {

    @Test
    public void testWhereString() {
        String clause = where("column", EQUAL, "value");

        assert clause.equals("column = 'value'");
    }

    @Test
    public void testWhereInt() {
        String clause = where("column", EQUAL, 1);

        assert clause.equals("column = 1");
    }

    @Test
    public void testWhereBoolean() {
        String clause = where("column", EQUAL, true);

        assert clause.equals("column = true");
    }

    @Test
    public void testAnd() {
        String clause = and( 
            where("column", EQUAL, 1), 
            where("other_column", DatabaseUtils.NOT_EQUAL, "value") 
        );

        System.out.println(clause);

        assert clause.equals("column = 1 AND other_column != 'value'");
    }

    @Test
    public void testOr() {
        String clause = or( 
            where("column", EQUAL, 1), 
            where("other_column", DatabaseUtils.NOT_EQUAL, "value") 
        );

        System.out.println(clause);

        assert clause.equals("column = 1 OR other_column != 'value'");
    }
}
