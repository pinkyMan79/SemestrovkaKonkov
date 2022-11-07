package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.Message;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MsgRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class ForumWriteMessageServlet extends HttpServlet {

    private MsgRepositoryImpl repository;

    @Override
    public void init() throws ServletException {

        repository = new MsgRepositoryImpl((DataSource) getServletContext().getAttribute("datasource"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String req_msgText = req.getParameter("msg_input");
        String req_sender = user.getNickname();
        String req_date = new Date().toString();
        Long req_uId = user.getId();

        Message message = Message.builder()
                .date(req_date)
                .uId(req_uId)
                .msgRoot(req_msgText)
                .sender(req_sender)
                .build();

        repository.save(message);

        req.getRequestDispatcher("/get_messages").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
