/*
package com.miusicmaker.musmk.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.File;

public class AbsolutePathForFileListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String absolute = System.getProperty("catalina.home");

        ServletContext context = sce.getServletContext();

        String relative = context.getInitParameter("tempfile.dir");

        File mFile = new File(absolute + File.separator + relative);

        if (!mFile.exists()){
            mFile.mkdirs();
        }

        context.setAttribute("all_files_in_directory", absolute + File.separator + relative);
        context.setAttribute("mus_file", mFile);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
*/
