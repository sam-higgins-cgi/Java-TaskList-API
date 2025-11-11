package com.myapp.tasklist.constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    
    private static final String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String user = "sa";
    private static final String password = "";

    private Database() {}

    public static String getUrl() {
        return url;
    }

    public static String getUserName() {
        return user;
    }

    private static String getPassword() {
        return password;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());

        return connection;
    }

}
