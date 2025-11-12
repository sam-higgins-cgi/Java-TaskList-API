package com.myapp.tasklist.services;

import static com.myapp.tasklist.utils.DatabaseUtils.EQUAL;
import static com.myapp.tasklist.utils.DatabaseUtils.where;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.myapp.tasklist.objects.TaskDto;

@Service
public class TasksService {

    private final JdbcTemplate jdbc;

    public TasksService(JdbcTemplate jbdc) {
        this.jdbc = jbdc;
    }

    private static final RowMapper<TaskDto> mapper = (rs, rowNum) -> new TaskDto(
        rs.getInt(TaskDto.ID),
        rs.getString(TaskDto.TITLE),
        rs.getBoolean(TaskDto.DONE),
        rs.getTimestamp(TaskDto.DATE_ADDED),
        rs.getTimestamp(TaskDto.DATE_DONE)
    );

    private List<TaskDto> getTaskList(String whereClause) {

        String query = String.format("SELECT * FROM tasks WHERE %s", whereClause);

        System.out.println("Executing query: " + query);
        
        return jdbc.query(query, mapper);
    }

    public List<TaskDto> getAllTaskList() {
        return getTaskList(where("1", EQUAL, "1"));
    }

    public List<TaskDto> getToDoTaskList() {
        return getTaskList(where(TaskDto.DONE, EQUAL, false));
    }
    
    public List<TaskDto> getDoneTaskList() {
        return getTaskList(where(TaskDto.DONE, EQUAL, true));
    }

    public TaskDto getTaskById(int id) {
        return getTaskList(where(TaskDto.ID, EQUAL, id)).get(0);
    }

    public void setNewTask(TaskDto dto) {

        String query = String.format("INSERT INTO tasks ( %s ) VALUES ( ? )", TaskDto.TITLE);
        
        jdbc.update(query, dto.task_title());
    }

    public void setTaskDone(TaskDto dto) {

        String query = String.format("UPDATE tasks SET %s = true, %s = CURRENT_TIMESTAMP() WHERE %s = ?", TaskDto.DONE, TaskDto.DATE_DONE, TaskDto.ID);

        jdbc.update(query, String.valueOf(dto.id()));

    }
        
}
