package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.Message;
import com.miusicmaker.musmk.repositories.MsgRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ForumLoadMessagesServlet extends HttpServlet {

    private MsgRepositoryImpl repository;

    @Override
    public void init() throws ServletException {
        Properties properties = TestConnection.getProperties();
        DataSource dataSource = new SimpleDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        repository = new MsgRepositoryImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Message> messages = repository.findAll();

        log(messages.toString());

        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/views/forum.jsp").forward(req, resp);

    }

}
