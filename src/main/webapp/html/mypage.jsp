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
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<!-- Navigation-->
<!-- Header-->
<%@ include file="header.jsp" %>

<section>
    <div class="text-black-center "> ${user.name} ${user.lastName} <br></div>
    <div class="fs-18">
        <c:if test="${user.banned==true}">
            <div class="text-center text-red fs-18">
                <br> You are banned!
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


        <c:set var="role" value="${sessionScope.role}"/>
        <c:if test="${role eq 'ADMIN'}">
            <div class="text-black-user">
                <div id="adminpage">
                    <a href="film-creating" class="btn default">Create new film</a>
                    <br> <br>
                    <p>You can find user by name and lastname to get more information about him</p>
                    <br>
                    <form action="user-search" method="get">
                    </form>
                    <form action="user-search" method="get">
                        <button type="submit" class="fa fa-search search-container"
                                href='search' value="search"><i></i></button>
                        <input type="search" class="search" name="search" placeholder="Search..">
                    </form>
                </div>
            </div>
        </c:if>
    </div>

</section>


</div>


</section>
<!-- #products -->


</section>


<!-- Footer-->
<%@ include file="footer.jsp" %>


</body>
</html>
