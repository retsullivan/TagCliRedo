package com.improving.tagcliredo.database;

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

    public void insertIntoTable() {
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon(Name, Area, ItemType) values('Smallish Dagger','Dagger Shop', 'weapon')");
            logger.info("Rows Affected : {}", rowsAffected);
        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }
    }

    public void readFromTable() {
        try {

        //List<String> names = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
        //(result, rowNum) ->
        //result.getString("Area"));
        //using a lambda function that maps the result to it's row number to get the area

        List<Weapon> weapons = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
             (result, rowNum) ->
                        new Weapon(result.getInt("Id"),
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));

        weapons.forEach(weapon -> logger.info("Weapon ID: {}, Name: {}", weapon.getId(), weapon.getName()));

        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }
    }
}
