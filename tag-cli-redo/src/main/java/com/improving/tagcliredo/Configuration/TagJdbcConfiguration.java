package com.improving.tagcliredo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class TagJdbcConfiguration {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //  http://google.com/this/path?key=value
        dataSource.setUrl("jdbc:mysql://localhost:3306/tag?serverTimezone=UTC");
        //mysql credentials
        dataSource.setUsername("retsullivan");
        dataSource.setPassword("JAVA2SQLPASSWORD");

        return dataSource;

    //Connection connection = DriverManager.getConnection(
    //"jdbc:mysql://localhost:3306/tag?serverTimezone=UTC",
    //"retsullivan",
    //"JAVA2SQLPASSWORD");
    }

}
