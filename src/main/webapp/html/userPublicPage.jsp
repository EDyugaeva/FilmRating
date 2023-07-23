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
            <div class="text-center" >User is banned! <br>
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
<%@ include file="footer.jsp" %>


</body>
</html>
