package com.miusicmaker.musmk.filters;

import javax.servlet.*;

import java.io.IOException;

// и фильтр тоже добавим

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String login = request.getParameter("username");

        String pass = request.getParameter("password");

        if (login != null && pass != null){

            request.getRequestDispatcher("/login").forward(request, response);

        }else {

            request.getRequestDispatcher("/index").forward(request, response);

        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
