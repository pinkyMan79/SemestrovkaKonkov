<%@ page import="com.miusicmaker.musmk.model.User" %><%--
  Created by IntelliJ IDEA.
  User: terenin
  Date: 23.10.2022
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

    <style>

        body {
            font-family: 'Montserrat', sans-serif;
            background: rgb(52, 56, 61);
        }

        .header-nightsky {
            color: white;
            background-size: cover;
            background: rgb(37, 40, 43);
            padding-bottom: 100px;
        }

        .header-nightsky .navbar-nav>li>a {
            color: white;
            font-size: 18px;
            border-radius: 10px;
        }

        .header-nightsky .navbar {
            margin-bottom: 0px;
            padding-top: 20px;
            padding-bottom: 20px;
            width: 100%;
            border-bottom:none;
            background-color: transparent;
            min-width: 300px;
            border: none;
        }

        .header-nightsky .navbar-default .navbar-nav>.open>a,
        .header-nightsky .navbar-default .navbar-nav>.open>a:focus,
        .header-nightsky .navbar-default .navbar-nav>.open>a:hover {
            color: #ccc;
            background-color: transparent;
        }

        .header-nightsky .nav>li>a:focus,
        .header-nightsky .nav>li>a:hover {
            color: #ccc;
            background-color: transparent;
        }

        .header-nightsky .navbar-nav>li {
            margin-right: 20px;
        }

        .header-nightsky .navbar-nav {
            margin-top: 12px;
        }

        .header-nightsky .navbar-toggle {
            background-color: transparent !important;
            margin-top: 20px;
            border: 1px solid #fff;
        }

        .header-nightsky .navbar-toggle .icon-bar {
            background-color: white;
        }

        .header-nightsky .navbar-brand {
            color: white;
            font-size: 50px;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .header-nightsky .navbar-brand:hover {
            color: #ccc;
        }

        .header-nightsky .hero {
            text-align: center;
            margin-top: 80px;
            margin-bottom: 100px;
        }

        .header-nightsky .hero h1 {
            color: white;
            font-weight: bold;
            font-size: 60px;
            margin-bottom: 36px;
        }


        .header-nightsky .hero p {
            color: rgba(255, 255, 255, 0.6);
            font-size: 22px;
            max-width: 660px;
            margin: 0 auto 20px;
            font-weight: normal;
            line-height: 1.5;
        }

        .header-nightsky .btn-primary {
            color: #fff;
            background-color: transparent;
            border-color: #fffbfb !important;
            outline:none;
            margin-right: 20px;
            margin-top: 20px;
            font-size: 24px;
            padding: 18px 24px;
            transition:0.2s background-color;
        }

        .header-nightsky .btn-primary:hover {
            background-color:rgba(255,255,255,0.1);
        }

        .header-nightsky .btn-primary:active {
            transform:translateY(1px);
        }

        @media screen and (max-width: 767px) {

            .header-nightsky .navbar-default .navbar-nav .open .dropdown-menu>li>a{
                color: #fff;
                font-size: 16px;
            }

            .header-nightsky .navbar-default .navbar-nav .open .dropdown-menu>li>a:hover{
                color: #ccc;
            }

            .header-nightsky .navbar-collapse {
                margin-left: 20px;
                border-top: none;
                box-shadow: none;
            }

            .header-nightsky .hero{
                margin-top: 40px;
                margin-bottom: 40px;
            }

            .header-nightsky .hero h1{
                font-size: 42px;
            }
        }




    </style>
</head>
<body>
<div class="header-nightsky">
    <nav class="navbar navbar-default">
        <div class="container">
            <a class="navbar-brand" href="#">Mus_vost</a>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">

                    <%
                        User user = (User) session.getAttribute("user");
                    %>

                    <li><a href="profile.jsp"><%=user.getNickname()%></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Па приколу)<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="sneak.jsp">Змий</a></li>
                            <li><a href="#">Попробуй разобраться с нотами</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> О сайте <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">За что тебе это?</a></li>
                            <li><a href="#">В чём фишка</a></li>
                            <li><a href="#">А теперь жми сюда</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="m_list">

    <table>

        <th>ID</th>
        <th>File Name</th>

        <%
            for (int i = 0; i < 20; i++) {
        %>

            <tr>
                <td></td>
                <td></td>
            </tr>

        <%
            }
        %>

    </table>

</div>

</body>
</html>