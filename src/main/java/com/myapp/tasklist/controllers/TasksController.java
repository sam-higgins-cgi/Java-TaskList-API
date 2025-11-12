package com.myapp.tasklist.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.tasklist.objects.TaskDto;
import com.myapp.tasklist.services.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasks;

    public TasksController(TasksService tasks) {
        this.tasks = tasks;
    }

    @GetMapping("/all")
    public List<TaskDto> getTasks() {
        return tasks.getAllTaskList();
    }

    @GetMapping("/todo")
    public List<TaskDto> getToDo() {
        return tasks.getToDoTaskList();
    }

    @GetMapping("/done")    
    public List<TaskDto> getDone() {
        return tasks.getDoneTaskList();
    }

    @GetMapping("/task")
    public TaskDto getMethodName(@RequestParam int id) {
        return tasks.getTaskById(id);
    }
    
    @PostMapping("/add")
    public void setNewTask(@RequestBody TaskDto entity) {
        tasks.setNewTask(entity);
    }

    @PatchMapping("/complete")
    public void setTaskDone(@RequestBody TaskDto entity) {
        tasks.setTaskDone(entity);
    }
}
