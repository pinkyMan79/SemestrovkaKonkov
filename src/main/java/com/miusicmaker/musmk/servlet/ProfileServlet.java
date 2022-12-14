package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MsgRepositoryImpl;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class ProfileServlet extends HttpServlet {

    private UserRepositoryImpl repository;

    public User user;

    @Override
    public void init() throws ServletException {

        repository = new UserRepositoryImpl((DataSource) getServletContext().getAttribute("datasource"));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.getRequestDispatcher("/main_page.jsp").forward(request,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String login = request.getParameter("username");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");

        HttpSession session = request.getSession();

        if (pass != null && pass.equals(re_pass)){

            if (email != null
                    //   && AuthValidator.isValidEmail(email)
                    && login != null){

                user = (User) session.getAttribute("user");

                user.setEmail(email);
                user.setNickname(login);
                user.setPassword(pass);

                repository.update(user);

                doGet(request, response);


            }else {

                PrintWriter out = response.getWriter();

                out.println("Form is not valid");

            }

        }else {

            PrintWriter out = response.getWriter();

            out.write("???????????? ???? ??????????????????");


        }

    }
}
