package com.miusicmaker.musmk;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class TestConnection {

    public static void main(String[] args) {

        Properties properties = getProperties();

        DataSource dataSource = new SimpleDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        System.out.println("CONNECTED TO DATABASE WITH PARAMS:" + "\n" + properties.getProperty("db.url") + " \n" +
                properties.getProperty("db.username") + " \n" +
                properties.getProperty("db.password"));

        UserRepositoryImpl repository = new UserRepositoryImpl(dataSource);

        repository.save(User.builder().password("1").nickname("2").email("em").build());

    }

    public static Properties getProperties(){

        Properties properties = new Properties();
        try {
            properties.load(TestConnection.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return properties;

    }

}
