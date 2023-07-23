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

    </div>
    <div class="text-black-center "> ${user.name} ${user.lastName}
    </div>
    <table class="table">
        <tr>
            <td>Status</td>
            <td>${user.status}</td>
        </tr>
        <tr>
            <td> Birthday :</td>
            <td> ${user.birthDate} </td>
        </tr>
        <tr>
            <td>Email :</td>
            <td> ${user.email} </td>
        </tr>
    </table>

    <c:if test="${user.banned==true}">
        <div class="text-center">
            You are banned! <br>
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

    <%--        <button type="button" class="btn default">Get all rates</button>--%>


</section>
<!-- Footer-->
<%@ include file="footer.jsp" %>


</body>
</html>
