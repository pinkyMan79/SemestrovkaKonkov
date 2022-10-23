package com.miusicmaker.musmk;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.Message;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MsgRepositoryImpl;
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

        MsgRepositoryImpl msgRepository = new MsgRepositoryImpl(dataSource);

        msgRepository.save(Message.builder().msgRoot("adsrgw").sender("guy").uId(3L).date("11.01.2012").build());

        System.out.println(msgRepository.findAll());

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
