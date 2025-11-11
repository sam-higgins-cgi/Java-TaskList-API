package com.myapp.tasklist.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.myapp.tasklist.schemas.Task;
import com.myapp.tasklist.tasks.Tasks;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TasksController {
    
    @GetMapping("/tasks/all")
    public JsonArray getTasks() throws SQLException {
        return Tasks.getAllTaskList();
    }

    @GetMapping("/tasks/todo")
    public JsonArray getToDo() throws SQLException {
        return Tasks.getToDoTaskList();
    }

    @GetMapping("/tasks/done")    public JsonArray getDone() throws SQLException {
        return Tasks.getDoneTaskList();
    }

    @PostMapping("/tasks/add")
    public void setNewTask(@RequestBody Task.Dto entity) throws SQLException {
        Tasks.setNewTask(entity);
    }

    @PatchMapping("/tasks/complete")
    public void setTaskDone(@RequestBody Task.Dto entity) throws SQLException {
        Tasks.setTaskDone(entity);
    }
}
