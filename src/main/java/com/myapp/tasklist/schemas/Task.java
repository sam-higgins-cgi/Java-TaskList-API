package com.myapp.tasklist.schemas;

import java.sql.Timestamp;

public final class Task {
    public static final String ID = "id";
    public static final String TASK = "task_title";
    public static final String DONE = "is_done";
    public static final String DATE_ADDED = "date_added";
    public static final String DATE_DONE = "date_done";

    public record Dto(
        int id, 
        String task_title, 
        boolean is_done, 
        Timestamp date_added, 
        Timestamp date_done
    ) {
    
        public Dto(String task_title) {
            this(-1, task_title, false, null, null);
        }

        public Dto(int id) {
            this(id, null, false, null, null);
        }

    }

}