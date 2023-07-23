<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<!-- Header-->
<%@ include file="header.jsp" %>
<!-- Section-->

<section id="search" class="text-black-left">
    <header></header>
    <div>
        <h2>Search users</h2>
        <form action="userSearch" method="get">
            <label>Search user by name and lastname</label> <input
                type="search" name="search">
            <input type="submit"
                   value="Search">
        </form>
        <br>
    </div>
    <p>Results: </p>
    <c:if test="${requestScope.users!=null}">
        <c:forEach items="${requestScope.users}"
                   var="user" varStatus="Loop">
            <c:set var="ratesList" value="${user.ratesList}"/>

            <div class="user-container text-black-left">
                    <%--        <a href="${pageContext.request.contextPath}/user?id=${user.id}">--%>
                    <%--            ></a>--%>
                    ${user.name} ${user.lastName} <br>
                Birthday: ${user.birthDate} <br>
                Numbers of votes: ${fn:length(ratesList)} <br>
                email: ${user.email}

                <a href="${pageContext.request.contextPath}/user?id=${user.id}">Get more information </a>

            </div>
        </c:forEach>
    </c:if>


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
