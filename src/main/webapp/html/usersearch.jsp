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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<%@ include file="header.jsp" %>
<section id="search" class="text-black-left">
    <header></header>
    <div>
        <h2>Search users</h2>
        <form action="user-search" method="get">
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
            <div class="user-container text-black-left">
                    ${user.name} ${user.lastName} <br>
                Birthday: ${user.birthDate} <br>
                email: ${user.email}
                <a href="${pageContext.request.contextPath}/user?id=${user.id}">Get more information </a>
            </div>
        </c:forEach>
    </c:if>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
