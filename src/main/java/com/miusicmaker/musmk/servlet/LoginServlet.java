package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class LoginServlet extends HttpServlet {

    private UserRepositoryImpl repository;

    public User user;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String password = request.getParameter("pass");

        if (login != null && password != null){

            user = repository.findByLoginAndPassword(login, password);

        }

        if (user == null){

            request.getRequestDispatcher("index.jsp").forward(request, response);

        }else {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("/main_page.jsp").forward(request, response);

        }
    }


}
