<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="Ekaterina Radomskaya" content=""/>
    <title>Films rating app</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<%@ include file="header.jsp" %>
<!-- Section-->
<section class="text-center colored">
    <div class=" row">
        <div class="column">
            <p class=text-black-center> Best movies <br>
                <c:if test="${requestScope.topFilms!=null}">
                <c:forEach items="${requestScope.topFilms}"
                           var="film" varStatus="Loop">
            <div>
                <p class="l-150 fa-lg">
                    <a href="${pageContext.request.contextPath}/film?id=${film.id}">
                            ${Loop.count}. ${film.title} </a>
                </p>

            </div>
            </c:forEach>
            </c:if>
            </p>
        </div>
        <div class="column">
            <p class=text-black-center> Users with highest status <br>
                <c:if test="${requestScope.topUsers!=null}">
                <c:forEach items="${requestScope.topUsers}"
                           var="user" varStatus="Loop">
            <div>
                <p class="l-150 fa-lg">
                    <a href="${pageContext.request.contextPath}/user?id=${user.id}">
                            ${Loop.count}. ${user.name} ${user.lastName} </a>
                </p>

            </div>
            </c:forEach>
            </c:if>
            </p>
            </p>
        </div>
    </div>

</section>

<!-- Footer-->
<%@ include file="footer.jsp" %>

</body>
</html>
