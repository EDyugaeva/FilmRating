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
<!-- Navigation-->
<!-- Header-->
<%@ include file="header.jsp" %>

<form>
    <div class="text-black-center "> ${user.name} ${user.lastName}</div>
    <div class="text-black-user">

        <div id="adminpage">

            <a href="filmCreating" class="btn default">Create new film</a>
            <br> <br>
            <p>You can find user by name and lastname to get more information about him</p>
            <br>

            <form action="userSearch" method="get">
            </form>

            <form action="userSearch" method="get">
                <button type="submit" class="fa fa-search search-container"
                        href='search' value="search"><i></i></button>
                <input type="search" class="search" name="search" placeholder="Search..">
            </form>

        </div>


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
