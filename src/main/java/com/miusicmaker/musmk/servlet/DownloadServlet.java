package com.miusicmaker.musmk.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {

    private ServletFileUpload uploader;

    private final int ARBITARY_SIZE = 1048;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename="+req.getParameter("filename"));

        try(InputStream in = req.getServletContext().getResourceAsStream(
                "/home/lino/Documents/musmk/src/main/webapp/WEB-INF/txtMidi/"
                        + req.getParameter("filename"));
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
