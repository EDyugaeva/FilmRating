<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
</head>
<body>
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
    <c:set var="isAuthorised" value="${sessionScope.isAuthorised}"/>
    <c:if test="${!isAuthorised}">
        <a href='login'>Log in</a>
    </c:if>
    <c:if test="${isAuthorised}">
        <a href='mypage'>My profile</a>
        <a href='logout'>Log out</a>
        <c:set var="role" value="${sessionScope.role}"/>
        <c:if test="${role eq 'ADMIN'}">
            <a href='film-creating'>Create new film</a>
        </c:if>
    </c:if>
</nav>
<header class="bg-new py-5 background">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Film rating</h1>
            <p class="lead fw-normal text-white-50 mb-0">Log in and discuss all films with your friends</p>
        </div>
    </div>
</header>
</body>
</html>
