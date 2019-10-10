package com.improving.tagcliredo;

import com.improving.tagcliredo.database.DatabaseClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TagCliRedoApplication implements CommandLineRunner {

    private final TagCliController tagCliController;

    public TagCliRedoApplication(TagCliController tagCliController) {
        this.tagCliController = tagCliController;
    }

    public static void main(String[] args) {
        SpringApplication.run(TagCliRedoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        tagCliController.inputProcesser();

    }
}
