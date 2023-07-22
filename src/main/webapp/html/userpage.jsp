<%@ page import="com.example.kinorate.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="Ekaterina Radomskaya" content=""/>
    <title>Films rating app</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>

<%@ include file="header.jsp" %>

<section>

    <div class="text-black-center "> ${user.name} ${user.lastName}
    </div>
    <div class="text-black-user">

        Status: ${user.status}<br>

        <c:if test="${user.banned==true}">
            <div class="text-red">User is banned! <br>
            </div>
        </c:if>
        <%--        Your favourites films: <br>--%>

        <%--        <p>--%>
        <%--        <ol>1. Film 1</ol>--%>
        <%--        <ol>2. Film 2</ol>--%>
        <%--        <ol>3. Film 3</ol>--%>
        <%--        <ol>4. Film 4</ol>--%>
        <%--        <ol>5. Film 5</ol>--%>

        <%--        </p>--%>

        <button type="button" class="btn default">Get all rates</button>
        <br> <br>
        Birthday : ${user.birthDate}%><br>
        <!--        <button class = "default">Change</button> <br>-->
        Email : ${user.email}
        <!--        <button class = "default">Change</button> <br>-->

    </div>

</section>
<!-- #products -->

<!-- Footer-->
<footer class="bg-new">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
