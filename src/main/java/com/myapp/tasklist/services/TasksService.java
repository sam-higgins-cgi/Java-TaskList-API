package com.myapp.tasklist.services;

import static com.myapp.tasklist.utils.DatabaseUtils.EQUAL;
import static com.myapp.tasklist.utils.DatabaseUtils.createWhereClause;

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
        
        return jdbc.query(query, mapper);
    }

    public List<TaskDto> getAllTaskList() {
        return getTaskList("1=1");
    }

    public List<TaskDto> getToDoTaskList() {
        return getTaskList(String.format(createWhereClause(TaskDto.DONE, EQUAL, "false")));
    }
    
    public List<TaskDto> getDoneTaskList() {
        return getTaskList(String.format(createWhereClause(TaskDto.DONE, EQUAL, "true")));
    }

    public List<TaskDto> getTaskById(int id) {
        return getTaskList(String.format(createWhereClause(TaskDto.ID, EQUAL, String.valueOf(id))));
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
