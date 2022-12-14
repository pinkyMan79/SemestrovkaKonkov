package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.dto.UserSignIn;
import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.mappers.Mappers;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;
import com.miusicmaker.musmk.validation.AuthValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;


public class HelloServlet extends HttpServlet {

    private UserRepositoryImpl repository;

    public User user;


    public void init() {

        repository = new UserRepositoryImpl((DataSource) getServletContext().getAttribute("datasource"));

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        session.setAttribute("user", user);

        request.getRequestDispatcher("/main_page.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String login = request.getParameter("username");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");

        if (pass != null && pass.equals(re_pass)){

            if (email != null
                    //&& AuthValidator.isValidEmail(email)
                    && login != null){

                UserSignIn signUp = new UserSignIn(email, pass, re_pass, login);

                user = Mappers.fromSignUpToUser.apply(signUp);

                repository.save(user);

                request.setAttribute("user", user);

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

    public void destroy() {
    }
}