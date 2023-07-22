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
    <div class="text-black-center " title="${user.name}"> ${user.name} ${user.lastName} </div>
    <div class="text-black-left">
        Status: ${user.status} <br>

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

        <%--        <button type="button" class="btn default">Get all rates</button>--%>

        Birthday : ${user.birthDate} <br>
        Email : ${user.email} <br>
        User: ${user}

    </div>

    <c:set var="userRole" value="${sessionScope.role}"/>

    <c:if test="${userRole!=null}">
        <c:if test="${userRole eq 'ADMIN'}">

            <div class="text-black-left">
                <div>You can ban or remove the ban from the user.</div>
                <form action="<c:url value="/ban"/>" method="post">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <c:if test="${user.banned==false}">
                        <button type="submit" name="ban" value="1" class="btn default">Ban</button>
                    </c:if>
                    <c:if test="${user.banned==true}">
                        <button type="submit" name="ban" value="0" class="btn default">Remove ban</button>
                    </c:if>
                    <br> <br>
                </form>

                <form action="status" method="post">
                    <div>You can change the user status:</div>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <select id="status" name="status">
                        <option name="status" value="1">Up</option>
                        <option name="status" value="-1">Down</option>
                    </select> <br>
                    <button type="submit" class="btn default">Submit</button>
                </form>

            </div>
        </c:if>
    </c:if>

</section>


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
