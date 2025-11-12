package com.myapp.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TaskList {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaskList.class, args);
	}
}
