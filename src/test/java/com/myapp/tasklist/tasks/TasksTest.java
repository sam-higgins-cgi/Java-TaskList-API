package com.myapp.tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myapp.tasklist.schemas.Task;

@SpringBootTest
public class TasksTest {

    Gson gson = new Gson();
    
    @Test
    public void getAllTaskListTest() throws SQLException{
        
        JsonArray results = Tasks.getAllTaskList();

        assertEquals(3, results.size());
    } 

    @Test
    public void getToDoTaskListTest() throws SQLException{
        
        JsonArray results = Tasks.getToDoTaskList();

        assertEquals(2, results.size());
    } 

    @Test
    public void getDoneTaskListTest() throws SQLException{
        
        JsonArray results = Tasks.getDoneTaskList();

        assertEquals(1, results.size());
    } 

    @Test
    public void getTaskByIdTest() throws SQLException{
        
        JsonArray results = Tasks.getTaskById(3);
        JsonObject object = results.get(0).getAsJsonObject();

        assertEquals(1, results.size(), "Returned results has one object");
        assertEquals(3, object.get(Task.ID).getAsInt(), "Returned object has expected id");
        
    } 
}
