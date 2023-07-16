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
<!-- Header-->
<%@ include file="header.jsp" %>

<!-- Section-->
<section class="text-center">
    <p>
    <div class="text-black-center"> Log in</div>
    <div class="text-black-left">
        <div> enter your email</div>
        <input type="text" placeholder="Username">
        <div> enter your password</div>
        <input type="text" placeholder="password"> <br>
        <button type="submit" class = "btn default">Log in</button>

        <div>You can create new account, if you do not have it</div>
        <button type="submit" class = "btn default" href='register'>Register</button>
        <a href="register">Register</a>

    </div>


    </p>


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
