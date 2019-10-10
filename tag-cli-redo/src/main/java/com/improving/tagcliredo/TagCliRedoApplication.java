package com.improving.tagcliredo;

import com.improving.tagcliredo.database.DatabaseClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TagCliRedoApplication implements CommandLineRunner {

    private final DatabaseClient databaseClient;

    public TagCliRedoApplication(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(TagCliRedoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        databaseClient.insertIntoTable();

        databaseClient.readFromTable();

        //	Class.forName("com.mysql.jdbc.Driver").newInstance();
        //newInstance is crossed out because it's deprecated
        //standardized way to connect to database
        // ODBC  OPEN Data Base Connector
        // jdbj is the java proprietary version
        //System.out.println("Hello Ms. World");

//		OldSkoolDatabaseClient client = new OldSkoolDatabaseClient();
//		client.insertRecordIntoDatabase();
//		client.readRecordFromDatabase();

    }
}
