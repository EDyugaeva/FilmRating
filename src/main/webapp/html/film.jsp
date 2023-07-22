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
    <link href="css/text.css" rel="stylesheet"/>
</head>
<body>
<!-- Header-->
<jsp:include page="header.jsp"></jsp:include>
<!-- Section-->
<section>
    <div class="text-black-center "> ${film.title} </div>
    <div class="section-film-card ">
        <img src="${film.image}" class="poster">
        <div class="p-l-10">
            ${film.description} <br>
            Rating: ${film.rate}<br>
            <%--               Количество голосов : <br>--%>
            <form action="rate" method="post">
                <div>Give your grade to the film!</div>
                <input type="hidden" name="film_id" value="${film.id}"/>

                <input type="radio" name="star" value="1"> 1
                <input type="radio" name="star" value="2"> 2
                <input type="radio" name="star" value="3"> 3
                <input type="radio" name="star" value="4"> 4
                <input type="radio" name="star" value="5"> 5
                <button type="submit"> Submit</button>

            </form>
        </div>

    </div>

</section>

<section class="fa-align-center">

    <c:if test="${requestScope.comments!=null}">
        <p>
        <c:forEach items="${requestScope.comments}"
                   var="comment" varStatus="Loop">
            <div class="section-comment">
                <h1 class="text-black-left-comment" style="">
                        ${comment.author.name} <br></h1>
                <a class="text-blue-italic">${comment.date} <br> </a>
                <div class="text-black-left-comment">
                        ${comment.text}<br></div>
                </p>
            </div>

        </c:forEach>
    </c:if>
    <h1>You can send your thought about this movie!</h1>
    <div class="section-comment">
        <form action="comment" method="POST">
            <input type="hidden" name="film_id" value="${film.id}"/>
            <label for="comment">Review of ${film.title}:</label>
            <textarea id="comment" name="comment" rows="4" cols="50"> Such a great movie! </textarea>
            <input type="submit" value="Submit">
        </form>

        <p>Click the "Submit" button and your comment will be sent to a page.</p>
    </div>
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
