package com.myapp.tasklist.utils;

public final class DatabaseUtils {

    public static String EQUAL = "=";
    public static String NOT_EQUAL = "!=";
    public static String GREATER_THAN = ">";
    public static String LESS_THAN = "<";
    public static String GREATER_THAN_EQUAL = ">=";
    public static String LESS_THAN_EQUAL = "<=";
    public static String LIKE = "LIKE";

    public static String where(String column, String operator, String value) {
        return String.format("%s %s '%s'", column, operator, value);
    }

    public static String where(String column, String operator, int value) {
        return String.format("%s %s %s", column, operator, value);
    }

    public static String where(String column, String operator, boolean value) {
        return String.format("%s %s %s", column, operator, value);
    }

    public static String and(String... clauses) {
        return String.join(" AND ", clauses);
    }
    
    public static String or(String... clauses) {
        return String.join(" OR ", clauses);
    }
}