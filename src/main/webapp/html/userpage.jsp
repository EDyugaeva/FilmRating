<%@ page import="com.example.kinorate.model.User" %>
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
    <link href="css/text.css" rel="stylesheet"/>
</head>
<body>

<%@ include file="header.jsp" %>


<form>
    <% User user = (User) session.getAttribute("user"); %>
    <div class="text-black-center " title="<%=user.getName()%>">  <%=user.getName()%> </div>
    <div class="text-black-user">

        Status: <br>
        Your favourites films: <br>

        <p>
        <ol>1. Film 1</ol>
        <ol>2. Film 2</ol>
        <ol>3. Film 3</ol>
        <ol>4. Film 4</ol>
        <ol>5. Film 5</ol>
        </p>

        <div id="personalpage">

            <button type="button" class="btn default">Get all rates</button>
            <br> <br>

            Birthday : <%=user.getBirthDate()%><br>
            <!--        <button class = "default">Change</button> <br>-->
            Email : <%=user.getEmail()%>
            <!--        <button class = "default">Change</button> <br>-->


        </div>
        <%if (session.getAttribute("role").equals(Role.ADMIN)) { %>}

        <div id="adminpage">

            <button type="button" class="btn default">Ban user</button>
            <br> <br>
            <button type="button" class="btn default">Up status</button>
            <br> <br>
            <button type="button" class="btn default">Down status</button>
            <br> <br>

        </div>
        <% } %>

    </div>

</form>


</div>


</section>
<!-- #products -->


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
