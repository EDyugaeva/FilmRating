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
<!-- Header-->
<%@ include file="header.jsp" %>


<section>
    <div class="text-center fa-2x my-3"> About this site</div>

    <div class="text-black-about">

        This is a capstone project for Epam-training. <br>
        Main goal was to create a web app using Java Servlets and html UI. <br>
        The administrator creates (manages) a list of films, serials.
        The user rates (once) the movie and can leave a review. <br>
        His status automatically increases (downgrades) if after a certain number of ratings (10) of other Users,
        if his rating is close (far) from the overall rating. <br>
        The administrator manages users: raises or lowers the status, puts bans.<br>

    </div>

    <div class="text-center fa-2x my-3"> About me</div>

    <div class="text-black-about">

        My name is Ekaterina Radomskaya. I am a Junior Java Developer.
        You can find me on <a href="redirect">LinkedIn</a>

    </div>

</section>
<!-- #products -->


</section>

<!-- Footer-->
<%@ include file="footer.jsp" %>
</body>
</html>
