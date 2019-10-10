package com.improving.tagcliredo;

import com.improving.tagcliredo.Models.Emote;
import com.improving.tagcliredo.database.DatabaseClient;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TagCliController {
    private final DatabaseClient databaseClient;
    Scanner scanner = new Scanner(System.in);
    String doThis;
    String tableType;
    String emoteName;
    String emoteMessage;


    public TagCliController(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }
    public void inputProcesser(){

        while (true){
            System.out.println("What do you want to do?");
            System.out.println("Add to a table: Press 1");
            System.out.println("Read from a table: Press 2");
            System.out.println("> ");
            doThis = scanner.nextLine();

            if (doThis.equalsIgnoreCase("1")){ //write to a SQL table
                System.out.println("What table do you want to modify?");
                System.out.println("Emote: Press 1");
                System.out.println("Weapon: Press 2");
                tableType = scanner.nextLine();

                    if (tableType.equalsIgnoreCase("1")){
                        System.out.println("What is the name of the emote?");
                        emoteName = scanner.nextLine();
                        System.out.println("What is the emote message?");
                        emoteMessage = scanner.nextLine();
                        Emote emote = new Emote(emoteName, emoteMessage);
                        databaseClient.insertEmote(emote);

                    } else if(tableType.equalsIgnoreCase("2")){
                        System.out.println("Weapon class not modified yet");
                    } else {
                        System.out.println("Invalid table name");
                    }

            } else if (doThis.equalsIgnoreCase("2")){ //read from a SQL table
                System.out.println("Read command still under construction");

            }else {
                System.out.println("Invalid command");
            }
        }
    }
}
