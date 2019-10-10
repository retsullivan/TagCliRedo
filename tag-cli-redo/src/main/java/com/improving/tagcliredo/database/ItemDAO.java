package com.improving.tagcliredo.database;

import com.improving.tagcliredo.Models.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ItemDAO {

        private final JdbcTemplate jdbcTemplate;
        Scanner scanner = new Scanner(System.in);
        //Item item = new Item;

        public ItemDAO(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        //Create Operation
        public boolean insertItem(Item item){
            try {
//                int rowsAffected = jdbcTemplate.update("INSERT INTO Item(Name, Weight, Value, Burnable, MagicQuality) values('"+
//                        item.getName()  + "'," + item.getWeight() + ","
//                        + item.getValue() + "," +item.getBurnable() + ",'"+
//                        item.getMagicQuality()+"')");
                int rowsAffected = jdbcTemplate.update("INSERT INTO Emote(Name, Weight, Value, Burnable, MagicQuality) " +
                        "values(?, ?, ?, ?, ?)", item.getName(), item.getWeight(), item.getValue(),
                        item.getBurnable(),item.getMagicQuality());

                System.out.println("Rows Affected: " + rowsAffected);
                return true;
            } catch(DataAccessException e){
                System.out.println("Exception throw in jbdc: " + e);
            }
            return false;
        }

        //Read Operation
        public Item selectItem(String name){
            try {
                List<Item> items = jdbcTemplate.query("SELECT * FROM item WHERE name LIKE '%" + name + "%'",
                        (result, rowNum) ->
                                new Item(
                                        result.getString("Name"),
                                        result.getInt("Weight"),
                                        result.getInt("Value"),
                                        result.getBoolean("Burnable"),
                                        result.getString("MagicQuality")
                                        ));

                items.forEach(item ->System.out.println("Name :" + item.getName()
                                                 + ":   Value: "+ item.getValue()
                                                 + ":   Weight: "+ item.getWeight()
                                                 + ":   Burnable: "+ item.getBurnable()
                                                 + ":   MagicQuality: "+ item.getMagicQuality()));
                if (items.size()==0){
                    return null;
                }
                return items.get(0);
            } catch(DataAccessException e){
                System.out.println("Exception throw in jbdc: " + e);
            } return null;
        }

        //Update Operation
        public boolean updateItem(Item item){
            throw new RuntimeException("Not Implemented Yet");
        }

        //Delete Operation

        public boolean deleteItem(Item item){
            throw new RuntimeException("Not Implemented Yet");
        }

}
