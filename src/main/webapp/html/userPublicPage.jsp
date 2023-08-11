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
    <div class="text-black-center mb-2" title="${user.name}"> ${user.name} ${user.lastName} </div>
    <div class="text-black">

        <c:if test="${user.banned==true}">
            <div class="text-center fs-18 text-red">User is banned! <br>
            </div>
        </c:if>

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


    </div>

    <c:set var="userRole" value="${sessionScope.role}"/>

    <c:if test="${userRole!=null}">
        <c:if test="${userRole eq 'ADMIN'}">

            <div class="text-black-left">
                <form action="<c:url value="/ban"/>" method="post">
                    <div>You can ban or remove the ban from the user.
                        <input type="hidden" name="id" value="${user.id}"/>
                        <c:if test="${user.banned==false}">
                            <button type="submit" name="ban" value="1" class="btn-2 back-red default">Ban</button>
                        </c:if>
                        <c:if test="${user.banned==true}">
                            <button type="submit" name="ban" value="0" class="btn-2 back-green default">Remove ban</button>
                        </c:if>
                    </div>
                </form>

                <form action="status" method="post">
                    <div>You can change the user status: <br>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <select id="status" name="status">
                            <option class="back-green" name="status" value="1">Up</option>
                            <option class="back-red" name="status" value="-1">Down</option>
                        </select>
                        <button type="submit" class="btn-2 default">Submit</button>
                    </div>
                </form>

            </div>
        </c:if>
    </c:if>

</section>


<!-- Footer-->
<%@ include file="footer.jsp" %>


</body>
</html>
