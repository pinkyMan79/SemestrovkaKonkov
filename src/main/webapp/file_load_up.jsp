<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: terenin
  Date: 04.11.2022
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="loadUpMusick" style="background: white">

    <form method="post" action="<c:url value="/upload"/>" enctype="multipart/form-data">
        Choose a file: <input type="file" name="multiPartServlet" />
        <input type="submit" value="Upload" />
    </form>

</div>

<%
    Path of = Path.of("/home/lino/Documents/musmk/src/main/webapp/WEB-INF/txtMidi");
    List<Path> fileList;
%>

<%

%>

      <%
          fileList = Files.walk(of)
                  .filter(Files::isRegularFile).toList();
          for (int i = 0; i < fileList.size(); i++) {%>

        <a href="<c:url value="/download"/>?filename=<%=fileList.get(i).getFileName()%>"><%=fileList.get(i).getFileName()%></a>

      <%}%>

    <%%>

</body>
</html>
