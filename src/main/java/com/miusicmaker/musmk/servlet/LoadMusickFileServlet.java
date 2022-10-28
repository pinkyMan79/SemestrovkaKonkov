package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MusicFileRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class LoadMusickFileServlet extends HttpServlet {

    MusicFileRepositoryImpl repository;

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    @Override
    public void init() throws ServletException {

        filePath = getServletContext().getInitParameter("file-upload");

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



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");

        isMultipart = ServletFileUpload.isMultipartContent((javax.servlet.http.HttpServletRequest) request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter( );

        if( !isMultipart ) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("/home/terenin/temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);

            // Process the uploaded file items
            Iterator<FileItem> i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if( fileName.lastIndexOf("/") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("/"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("/")+1)) ;
                    }
                    fi.write( file ) ;
                    out.println("Uploaded Filename: " + fileName + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");



        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
