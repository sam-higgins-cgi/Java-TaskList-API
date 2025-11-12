package com.myapp.tasklist.utils;

public final class DatabaseUtils {

    public static String EQUAL = "=";
    public static String NOT_EQUAL = "!=";
    public static String GREATER_THAN = ">";
    public static String LESS_THAN = "<";
    public static String GREATER_THAN_EQUAL = ">=";
    public static String LESS_THAN_EQUAL = "<=";
    public static String LIKE = "LIKE";

    public static String createWhereClause(String column, String operator, String value) {
        return String.format("%s %s %s", column, operator, value);
    }
    
}