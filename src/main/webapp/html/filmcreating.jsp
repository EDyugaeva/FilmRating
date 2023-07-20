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

<form action="filmCreatin" method="post" enctype="multipart/form-data">
    <div class="container">
        <h1>Create a new film</h1>
        <p>Please fill in this form to create a new film.</p>
        <form action="film" method="post">
            <label for="title"><b>Title</b></label>
            <input type="text" placeholder="Enter title" name="title" id="title" required>

            <label for="description"><b>Description</b></label>
            <input type="text" placeholder="Enter description" name="description" id="description" required>

            <input type="file" id="file" name="file">

            <button type="submit" class="savebtn">Save!</button>
        </form>
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
