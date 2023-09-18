<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<section>
    <p class="text-black-center"> Log in</p>
    <div class="text-black-left">
        <form action="login" method="post">
            <div> enter your email</div>
            <input type="text" placeholder="email" name="email">
            <div> enter your password</div>
            <input type="text" placeholder="password" name="password"> <br>
            <button type="submit" class="btn default">Log in</button>
        </form>
        <c:set var="isAuthorised" value="${sessionScope.isAuthorised}"/>
        <c:if test="${isAuthorised}">
            <div class="text-green">
                You successfully logged in! <br>
            </div>
        </c:if>
        <c:if test="${!isAuthorised}">
            <div>You can create new account, if you do not have it</div>
            <br>
            <a class="btn default" href='register'>Register</a>
        </c:if>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
