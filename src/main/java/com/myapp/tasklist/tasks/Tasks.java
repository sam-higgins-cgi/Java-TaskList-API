package com.myapp.tasklist.tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.myapp.tasklist.constants.Database;
import com.myapp.tasklist.schemas.Task;

public class Tasks {

    private static Gson gson = new Gson();

    private static JsonArray getTaskList(String whereClause) throws SQLException {
        
        try (
            Connection conn = Database.getConnection();
        ) {
            PreparedStatement statement = conn.prepareStatement(String.format("SELECT * FROM tasks WHERE %s", whereClause));

            ResultSet results = statement.executeQuery();
            
            JsonArray json = new JsonArray();
            
            while (results.next()) {

                Task.Dto task = new Task.Dto(
                    results.getInt(Task.ID),
                    results.getString(Task.TASK),
                    results.getBoolean(Task.DONE),
                    results.getTimestamp(Task.DATE_ADDED),
                    results.getTimestamp(Task.DATE_DONE)
                );

                json.add(gson.toJsonTree(task));
        
            }
            
            return json;
        }
    }

    public static JsonArray getAllTaskList() throws SQLException {
        return getTaskList("1=1");
    }

    public static JsonArray getToDoTaskList() throws SQLException {
        return getTaskList(String.format("%s = false", Task.DONE));
    }
    
    public static JsonArray getDoneTaskList() throws SQLException {
        return getTaskList(String.format("%s = true", Task.DONE));
    }

    public static JsonArray getTaskById(int id) throws SQLException {
        return getTaskList(String.format("%s = %d", Task.ID, id));
    }

    public static void setNewTask(Task.Dto dto) throws SQLException {
        try (
            Connection conn = Database.getConnection();
        ) {
            PreparedStatement statement = conn.prepareStatement(
                String.format(
                    "INSERT INTO tasks ( %s ) VALUES ( ? )", 
                    Task.TASK
                )
            );

            statement.setString(1, dto.task_title());

            statement.executeUpdate();
        }
    }

    public static void setTaskDone(Task.Dto dto) throws SQLException{
        try (
            Connection conn = Database.getConnection();
        ) {
            PreparedStatement statement = conn.prepareStatement(
                String.format(
                    "UPDATE tasks SET %s = true, %s = CURRENT_TIMESTAMP() WHERE %s = ?", 
                    Task.DONE, 
                    Task.DATE_DONE, 
                    Task.ID
                )
            );

            statement.setInt(1, dto.id());

            statement.executeUpdate();
        }
        
    }
        
}
