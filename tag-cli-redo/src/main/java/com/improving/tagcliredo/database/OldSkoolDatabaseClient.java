package com.improving.tagcliredo.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OldSkoolDatabaseClient {

    //use the slf4j logger
    private static final Logger logger = LoggerFactory.getLogger(OldSkoolDatabaseClient.class);

    public void insertRecordIntoDatabase() throws Exception {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            //System.out.println("Connection Made");
            logger.info("Connection + Statement made");

            int rowsAffected = statement.executeUpdate("INSERT INTO Weapon(Name, Area, ItemType) VALUES('Dagger', ' Test Area ', ' Test Weapon ')");
            if (rowsAffected >0){
                logger.info("Committing...");
                connection.commit();
            }
            logger.info("rows affected: " + rowsAffected);


        }catch (SQLException e){
        //  System.out.println(e.getStackTrace());
        //  System.out.println(e.getErrorCode());
        //  System.out.println(e.getSQLState());
            logger.error(e.getMessage());
        }
        //finally is being called automatically here and doing connection.close();
        // this is because Connection extends AutoClosable
    }

    public void readRecordFromDatabase() throws Exception {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            logger.info("Connection + Statement made");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM weapon LIMIT 10");
            ResultSetMetaData metaData = resultSet.getMetaData();
            String columns = "";
            for (int i = 1 ; i < metaData.getColumnCount(); i++) {
                if (i<metaData.getColumnCount()-1) {
                    columns = columns + "( i )"+ metaData.getColumnName(i) + ",";
                }
                else {columns = columns +"( i )"+ metaData.getColumnName(i);}
            }
            logger.info("Table of Columns: " + columns);

            resultSet.beforeFirst(); //set the row counter to "0"
            while(resultSet.next()){ //iterates by 1, and checks to see there's another row
                //int type = resultSet.getType();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(3);
                String area = resultSet.getString(17);
                logger.info("ID: {}, Name: {}, Area: {}", id, name, area);
                //the curly brace {} is a special 'insert' object here
                //works for logger.info or logger.error
            }

            resultSet.close(); //don't close before you read from it though

        }catch (SQLException e){
            //  System.out.println(e.getStackTrace());
            //  System.out.println(e.getErrorCode());
            //  System.out.println(e.getSQLState());
            logger.error(e.getMessage());
        }
        //finally is being called automatically here and doing connection.close();
        // this is because Connection extends AutoClosable

    }






    private Connection getConnection() throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tag?serverTimezone=UTC",
                "retsullivan",
                "JAVA2SQLPASSWORD");
        connection.setAutoCommit(false);
        return connection;
    }
}