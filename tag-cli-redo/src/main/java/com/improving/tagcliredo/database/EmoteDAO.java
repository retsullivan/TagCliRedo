package com.improving.tagcliredo.database;

import com.improving.tagcliredo.Models.Emote;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class EmoteDAO { //Emote DataAccess Object

    private final JdbcTemplate jdbcTemplate;
    Scanner scanner = new Scanner(System.in);
    //Emote emote = new Emote;

    public EmoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Create Operation
    public boolean insertEmote(Emote emote){
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO Emote(Name, Message) values('"+
                    emote.getName()  + "','" + emote.getMessage() + "')");
            System.out.println("Rows Affected: " + rowsAffected);
            return true;
        } catch(DataAccessException e){
            System.out.println("Exception throw in jbdc: " + e);
        }
        return false;
    }


    //Read Operation

    public Emote selectEmote(String name){
        try {
        List<Emote> emotes = jdbcTemplate.query("SELECT * FROM emote WHERE Name = '" + name + "'",
                (result, rowNum) ->
                        new Emote(
                                result.getString("Name"),
                                result.getString("Message")));

        emotes.forEach(emote ->System.out.println(emote.getName() + ": "+ emote.getMessage()));
            if (emotes.size()==0){
                return null;
            }
            return emotes.get(0);
    } catch(DataAccessException e){
        System.out.println("Exception throw in jbdc: " + e);
    } return null;
    }

    //Update Operation
    public boolean updateEmote(Emote emote){
        throw new RuntimeException("Not Implemented Yet");
    }

    //Delete Operation

    public boolean deleteEmote(Emote emote){
        throw new RuntimeException("Not Implemented Yet");
    }





}
