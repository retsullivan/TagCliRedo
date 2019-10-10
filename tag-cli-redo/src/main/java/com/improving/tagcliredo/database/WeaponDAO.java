package com.improving.tagcliredo.database;

import com.improving.tagcliredo.Models.Emote;
import com.improving.tagcliredo.Models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class WeaponDAO {

    private final JdbcTemplate jdbcTemplate;
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(DatabaseClient.class);


    public WeaponDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Create Operation
    public boolean insertWeapon(Weapon weapon){
        try {
//            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon(Name, Area, ItemType) values('"
//                    + weapon.getName()+ "','" +weapon.getArea() + "','weapon')");

            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon(Name, Area, ItemType) values(?, ?, ?)",
                    weapon.getName(), weapon.getArea(), "weapon");



            logger.info("Rows Affected : {}", rowsAffected);
            return true;
        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }
        return false;

    }

    //Read Operation

    public Weapon selectWeapon(String name){
        try {
            List<Weapon> weapons = jdbcTemplate.query("SELECT * FROM weapon WHERE name LIKE '%" + name + "%'",
                    (result, rowNum) ->
                            new Weapon(
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));

            weapons.forEach(weapon -> logger.info("Name: {}, Area: {}", weapon.getName(), weapon.getArea()));
            if (weapons.size()==0){
                return null;
            }
            return weapons.get(0);
        } catch(DataAccessException e){
            logger.error("Exception throw in jbdc: ", e);
        }return null;

    }
    //Update Operation
    public boolean updateWeapon(Emote emote){
        throw new RuntimeException("Not Implemented Yet");
    }

    //Delete Operation

    public boolean deleteWeapon(Emote emote){
        throw new RuntimeException("Not Implemented Yet");
    }

}
