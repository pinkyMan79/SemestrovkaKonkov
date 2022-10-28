package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AccountsServlet extends HttpServlet {

    private UserRepositoryImpl repository;

    @Override
    public void init() throws ServletException {
        Properties properties = TestConnection.getProperties();
        DataSource dataSource = new SimpleDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        repository = new UserRepositoryImpl(dataSource);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = repository.findAll();

        req.setAttribute("accounts_list", users);

        req.getRequestDispatcher("/accounts_page.jsp").forward(req, resp);

    }


}
