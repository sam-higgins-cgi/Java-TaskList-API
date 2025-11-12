package com.myapp.tasklist.services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final DataSource datasource;

    public DatabaseService(DataSource datasource) {
        this.datasource = datasource;
    }

    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}