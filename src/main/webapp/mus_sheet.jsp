<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.miusicmaker.musmk.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="org.jfugue.player.Player" %><%--
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
                            <li><a href="<c:url value="/get_notes"/>">Попробуй разобраться с нотами</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> О сайте <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">За что тебе это?</a></li>
                            <li><a href="<c:url value="/get_messages"/>">Форум</a></li>
                            <li><a href="#">В чём фишка</a></li>
                            <li><a href="#">А теперь жми сюда</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div style="color: aqua; background: rgba(199,199,199,0.1)">
    <table>

        <th>Notes</th>

        <%


            if (!(request.getAttribute("readyNotes") == null)){

                List<String> notes = (List<String>) request.getAttribute("readyNotes");
                for (String note : notes) {
        %>

        <tr>
            <td><%=note%></td>
        </tr>

        <%}}else {%>

        <h1>Notes are null</h1>

        <%}%>

    </table>
</div>

<div class="m_list">

    <form method="get" action="<c:url value="/get_notes"/>">

        <p>Выбери октаву</p>
        <p><input name="octave" value="1"></p>


        <table>

            <%
                if (request.getAttribute("octave") == null){
            %>

            <tr>
                <td><input type="submit" value="C1"></td>
                <td><input type="submit" value="C#1"></td>
                <td><input type="submit" value="D1"></td>
                <td><input type="submit" value="D#1"></td>
                <td><input type="submit" value="E1"></td>
                <td><input type="submit" value="F1"></td>
                <td><input type="submit" value="F#1"></td>
                <td><input type="submit" value="G1"></td>
                <td><input type="submit" value="G#1"></td>
                <td><input type="submit" value="A1"></td>
                <td><input type="submit" value="A#1"></td>
                <td><input type="submit" value="B1"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 1){
            %>

            <tr>
                <td><input type="submit" value="C1" name="C1"></td>
                <td><input type="submit" value="C#1" name="C#1"></td>
                <td><input type="submit" value="D1" name="D1"></td>
                <td><input type="submit" value="D#1" name="D#1"></td>
                <td><input type="submit" value="E1" name="E1"></td>
                <td><input type="submit" value="F1" name="F1"></td>
                <td><input type="submit" value="F#1" name="F#1"></td>
                <td><input type="submit" value="G1" name="G1"></td>
                <td><input type="submit" value="G#1" name="G#1"></td>
                <td><input type="submit" value="A1" name="A1"></td>
                <td><input type="submit" value="A#1" name="A#1"></td>
                <td><input type="submit" value="B1" name="B1"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 2){
            %>

            <tr>
                <td><input type="submit" value="C2" name="C2"></td>
                <td><input type="submit" value="C#2" name="C#2"></td>
                <td><input type="submit" value="D2" name="D2"></td>
                <td><input type="submit" value="D#2" name="D#2"></td>
                <td><input type="submit" value="E2" name="E2"></td>
                <td><input type="submit" value="F2" name="F2"></td>
                <td><input type="submit" value="F#2" name="F#2"></td>
                <td><input type="submit" value="G2" name="G2"></td>
                <td><input type="submit" value="G#2" name="G#2"></td>
                <td><input type="submit" value="A2" name="A2"></td>
                <td><input type="submit" value="A#2" name="A#2"></td>
                <td><input type="submit" value="B2" name="B2"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 3){
            %>

            <tr>
                <td><input type="submit" value="C3" name="C3"></td>
                <td><input type="submit" value="C#3" name="C#3"></td>
                <td><input type="submit" value="D3" name="D#3"></td>
                <td><input type="submit" value="D#3" name="D#3"></td>
                <td><input type="submit" value="E3" name="E3"></td>
                <td><input type="submit" value="F3" name="F3"></td>
                <td><input type="submit" value="F#3" name="F#3"></td>
                <td><input type="submit" value="G3" name="G3"></td>
                <td><input type="submit" value="G#3" name="G#3"></td>
                <td><input type="submit" value="A3" name="A3"></td>
                <td><input type="submit" value="A#3" name="A#3"></td>
                <td><input type="submit" value="B3" name="B3"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 4){
            %>

            <tr>
                <td><input type="submit" value="C4" name="C4"></td>
                <td><input type="submit" value="C#4" name="C#4"></td>
                <td><input type="submit" value="D4" name="D#4"></td>
                <td><input type="submit" value="D#4" name="D#4"></td>
                <td><input type="submit" value="E4" name="E4"></td>
                <td><input type="submit" value="F4" name="F4"></td>
                <td><input type="submit" value="F#4" name="F#4"></td>
                <td><input type="submit" value="G4" name="G4"></td>
                <td><input type="submit" value="G#4" name="G#4"></td>
                <td><input type="submit" value="A4" name="A4"></td>
                <td><input type="submit" value="A#4" name="A#4"></td>
                <td><input type="submit" value="B4" name="B4"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 5){
            %>

            <tr>
                <td><input type="submit" value="C5" name="C5"></td>
                <td><input type="submit" value="C#5" name="C#5"></td>
                <td><input type="submit" value="D5" name="D#5"></td>
                <td><input type="submit" value="D#5" name="D#5"></td>
                <td><input type="submit" value="E5" name="E5"></td>
                <td><input type="submit" value="F5" name="F5"></td>
                <td><input type="submit" value="F#5" name="F#5"></td>
                <td><input type="submit" value="G5" name="G5"></td>
                <td><input type="submit" value="G#5" name="G#5"></td>
                <td><input type="submit" value="A5" name="A5"></td>
                <td><input type="submit" value="A#5" name="A#5"></td>
                <td><input type="submit" value="B5" name="B5"></td>
            </tr>

            <%}%>

            <%
                if ((int)request.getAttribute("octave") == 6){
            %>

            <tr>
                <td><input type="submit" value="C6" name="C6"></td>
                <td><input type="submit" value="C#6" name="C#6"></td>
                <td><input type="submit" value="D6" name="D#6"></td>
                <td><input type="submit" value="D#6" name="D#6"></td>
                <td><input type="submit" value="E6" name="E6"></td>
                <td><input type="submit" value="F6" name="F6"></td>
                <td><input type="submit" value="F#6" name="F#6"></td>
                <td><input type="submit" value="G6" name="G6"></td>
                <td><input type="submit" value="G#6" name="G#6"></td>
                <td><input type="submit" value="A6" name="A6"></td>
                <td><input type="submit" value="A#6" name="A#6"></td>
                <td><input type="submit" value="B6" name="B6"></td>
            </tr>

            <%}%>

        </table>



    </form>

</div>
<form method="get" action="<c:url value="/play_notes"/>">

    <input name="tempo" value="120">
    <input name="instrument" value="Steel_String_Guitar">
    <input type="submit" value="Проиграть ноты">

</form>
<div>

    <%

        File file = (File) request.getAttribute("music");

        if (file == null){

            file = new File("teraa.mp3");

        }else {

            file = (File) request.getAttribute("music");

        }

    %>

    <audio controls autoplay>
        <source src="<%=file.getAbsolutePath() + file.getName()%>" type="audio/*">
    </audio>

</div>

<div class="loadUpMusick" style="background: white">

    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/load" method="post">
        <p>Выбери файл на устройстве, пжалста</p>
        <p><input type="file" name="music_file" multiple accept="audio/*">
            <label for="mf_name"> Назови как-нибудь, попробуй rap_for_dodiks
                <input id="mf_name" type="text" name="mf_name" placeholder="name">
            </label>
            <input type="submit" value="Отправить"></p>
    </form>

</div>

</body>
</html>
