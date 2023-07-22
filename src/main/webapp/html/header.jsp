<%@ page import="com.example.kinorate.model.Role" %><%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 16.07.2023
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
</head>
<body>

<!-- Navigation-->
<nav class="topnav">
    <a href='about'>About</a>
    <a href='home'>Main</a>
    <div class="topnav-right">
        <form action="search" method="get">
            <button type="submit" class="fa fa-search search-container"
                    href='search' value="search"><i></i></button>
            <input type="search" class="search" name="search" placeholder="Search..">
        </form>


    </div>
    <%
        if (session.getAttribute("isAuthorised") == null) {
    %>
    <a href='login'>Log in</a>
    <% } else { %>
    <a href='mypage'>My profile</a>
    <a href='logout'>Log out</a>
    <% }
        if (session.getAttribute("role") != null && session.getAttribute("role").equals(Role.ADMIN)) { %>
    <a href='filmCreating'>Create new film</a>
    <% } %>
</nav>

<!-- Header-->
<header class="bg-new py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Film rating</h1>
            <p class="lead fw-normal text-white-50 mb-0">Log in and discuss all films with your friends</p>
        </div>
    </div>
</header>

</body>
</html>
