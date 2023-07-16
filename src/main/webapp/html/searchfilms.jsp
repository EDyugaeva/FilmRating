<%@ page import="com.example.kinorate.model.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" errorPage="error.jsp" isErrorPage="false" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="Ekaterina Radomskaya" content=""/>
    <title>Films rating app</title>
    <!-- Favicon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/text.css" rel="stylesheet"/>
</head>
<body>
<!-- Header-->
<%@ include file="header.jsp" %>

<section>
    <section id="search" class="text-black-left">
        <header></header>
        <div>
            <h2>Search films</h2>
            <form action="search" method="get">
                <label>Search your film</label>
                <input
                        type="search" name="search">
                <input type="submit"
                       value="Search">
            </form>
        </div>
    </section>
    <section>
        <%
            List<Film> films = (List<Film>) request.getAttribute("films");
            Iterator<Film> iterator = films.iterator();

            while (iterator.hasNext()) {
                Film film = iterator.next();

        %>
        <form class="filmContainerItem">
            <img src="https://avatars.mds.yandex.net/get-kinopoisk-image/4774061/2c159225-2a48-4d89-9af8-0f880fe1aea5/1920x"
                 width="200" height="300">
            <div class="text-black-left">
                <%=film.getTitle()%> <br>
                <%=film.getDescription()%> <br>
                Rate: <%=film.getRate()%><br>
                Numbers of votes: <br>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
            </div>
        </form>
        <%
            }
        %>

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
