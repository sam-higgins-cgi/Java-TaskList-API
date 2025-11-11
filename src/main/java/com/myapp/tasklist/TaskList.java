package com.myapp.tasklist;

import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myapp.tasklist.constants.Database;

@SpringBootApplication
public class TaskList {

	public static void main(String[] args) throws Exception {
		
		initialiseDatabase();

		SpringApplication.run(TaskList.class, args);
	}

	private static void initialiseDatabase() throws Exception {

		try (
			Connection connection = Database.getConnection();
			Statement statement = connection.createStatement()
		) {
			String schema_statement = Files.readString(Path.of("src/main/resources/schema.sql"));
			String data_statement = Files.readString(Path.of("src/main/resources/data.sql"));

			statement.executeUpdate(schema_statement);
			statement.executeUpdate(data_statement);

			connection.close();
		}
	} 

}
