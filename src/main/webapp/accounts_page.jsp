<%@ page import="java.util.List" %>
<%@ page import="com.miusicmaker.musmk.model.User" %><%--
  Created by IntelliJ IDEA.
  User: terenin
  Date: 20.10.2022
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    
    <th>ID</th>
    <th>Email</th>
    <th>Login</th>
    <th>Password</th>
    
    <%
        List<User> users = (List<User>) request.getAttribute("accounts_list");
        for (int i = 0; i < users.size(); i++) {
    %>

    <tr>

        <td><%=users.get(i).getId()%></td>
        <td><%=users.get(i).getEmail()%></td>
        <td><%=users.get(i).getNickname()%></td>
        <td><%=users.get(i).getPassword()%></td>

    </tr>

    <%}%>
    
</table>
</body>
</html>
