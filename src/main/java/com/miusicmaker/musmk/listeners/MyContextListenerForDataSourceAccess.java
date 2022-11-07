package com.miusicmaker.musmk.listeners;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.servlet.TestConnection;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.util.Properties;

public class MyContextListenerForDataSourceAccess implements ServletContextListener {

    public DataSource getDataSource(){

        Properties properties = TestConnection.getProperties();

        return new SimpleDataSource(

                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")

        );

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        DataSource dataSource = getDataSource();

        context.setAttribute("datasource", dataSource);

    }

}
