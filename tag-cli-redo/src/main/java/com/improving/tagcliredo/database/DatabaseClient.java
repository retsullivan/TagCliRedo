package com.improving.tagcliredo.database;

import com.improving.tagcliredo.Models.Emote;
import com.improving.tagcliredo.Models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseClient {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseClient.class);
    private final JdbcTemplate jdbcTemplate;

    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertEmote(Emote emote){
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO Emote(Name, Message) values('"+
                    emote.getName()  + "','" + emote.getMessage() + "')");
            logger.info("Rows Affected : {}", rowsAffected);
        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }
    }

    public void insertWeapon(Weapon weapon) { //insertweapon
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon(Name, Area, ItemType) values('"
                    +weapon.getName()+ "','" +weapon.getArea() + "','weapon')");
            logger.info("Rows Affected : {}", rowsAffected);
        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }
    }

    public void readFromTable() {

        try {
        List<Weapon> weapons = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
             (result, rowNum) ->
                        new Weapon(
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));

        weapons.forEach(weapon -> logger.info("Name: {}, Area{}, Item Type{}", weapon.getName(), weapon.getArea()));

        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }



    }
}
