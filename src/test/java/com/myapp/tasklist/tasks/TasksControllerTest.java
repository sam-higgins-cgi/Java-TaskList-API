package com.myapp.tasklist.tasks;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TasksControllerTest {

    @Autowired MockMvc mvc;

    @Test
    public void getAllTaskListTest() throws Exception {
        mvc.perform(get("/tasks/all")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(3))
        );
    } 

    @Test
    public void getToDoTaskListTest() throws Exception{
        mvc.perform(get("/tasks/todo")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(2))
        );
    } 

    @Test
    public void getDoneTaskListTest() throws Exception{
        mvc.perform(get("/tasks/done")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(1))
        );
    } 

    @Test
    public void getTaskByIdTest() throws Exception{
        mvc.perform(get("/tasks/done")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(1))
        );
    } 

    @Test
    @DirtiesContext
    public void postNewTaskTest() throws Exception{
        mvc.perform(post("/tasks/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"task_title\": \"Do Test\"}")
        ).andExpect(status().isOk());
        
        mvc.perform(get("/tasks/all")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(4))
        );
    } 

    @Test
    @DirtiesContext
    public void patchCompleteTaskTest() throws Exception{
        mvc.perform(patch("/tasks/complete")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": \"1\"}")
        ).andExpect(status().isOk());;
        
        mvc.perform(get("/tasks/done")).andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(2))
        );
    } 

    @AfterEach
    public void show() throws Exception{
        System.err.println("AFTER: " + mvc.perform(get("/tasks/all")).andReturn().getResponse().getContentAsString());
    }

}
