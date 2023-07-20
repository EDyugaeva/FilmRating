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
    <link href="../css/text.css" rel="stylesheet"/>
</head>
<body>
<!-- Header-->
<%@ include file="header.jsp" %>
<!-- Section-->
<form>
    <div class="text-black-center "> Название фильма ${product.title}</div>
    <div class="section-film-card ">
        <img class="poster"
             src="https://avatars.mds.yandex.net/get-kinopoisk-image/4774061/2c159225-2a48-4d89-9af8-0f880fe1aea5/1920x"
             vspace="5" hspace="5">
        <div class="p-l-10">
            Описание <br>
            Рейтинг: 7.9<br>
            Количество голосов : <br>

            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <div id="comment" class="section-comment">
                <p>
                    <h1 class="text-black-left-comment" style="">
                        user name <br> </h1>

                    <a class="text-blue-italic">and time <br> </a>

                    <a class="text-black-left-comment">
                        comment <br> </a>



                </p>
            </div>

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
