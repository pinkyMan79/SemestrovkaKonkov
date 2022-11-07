package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.MusicFile;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MusicFileRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private MusicFileRepositoryImpl repository;

    @Override
    public void init() throws ServletException {

        Properties properties = TestConnection.getProperties();
        DataSource dataSource = new SimpleDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        repository = new MusicFileRepositoryImpl(dataSource);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/file_load_up.jsp").forward(req, resp);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uploadPath = "/WEB-INF/"; //(String) getServletContext().getAttribute("all_files_in_directory");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        for (Part part : req.getParts()) {
            String fileName = part.getSubmittedFileName();
            part.write(File.separator + fileName);
        }
        doGet(req, resp);

    }


}
