package com.myapp.tasklist.schemas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    
    @Test
    public void TaskDtoFromTaskTitleTest() {

        Task.Dto result = new Task.Dto("test");

        assertEquals("test", result.task_title());
    }

    @Test
    public void TaskDtoFromTaskIdTest() {

        Task.Dto result = new Task.Dto(1);

        assertEquals(1, result.id());
    }

}
