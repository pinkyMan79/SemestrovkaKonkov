package com.miusicmaker.musmk.listeners;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.servlet.TestConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.util.Properties;

public class MyContextListenerForDataSourceAccess implements ServletContextListener {

    public DataSource getDataSource(){

        Properties properties = TestConnection.getProperties();

        DataSource dataSource = new SimpleDataSource(

                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")

        );

        return dataSource;

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        DataSource dataSource = getDataSource();

        context.setAttribute("datasource", dataSource);

    }

}
