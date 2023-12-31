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
    <form action="register" method="post">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <form action="register" method="post">

                <label for="name"><b>Name</b></label>
                <input type="text" placeholder="Enter name" name="name" id="name" required>

                <label for="last-name"><b>Last name</b></label>
                <input type="text" placeholder="Enter last name" name="last-name" id="last-name" required>


                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" id="email" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="psw" required>

                <label for="psw-repeat"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="password-repeat" id="psw-repeat" required>

                <label for="birth-day"><b>Fill in your birthday date</b></label><br>
                <input type="date" PLACEHOLDER="place" name="birth-day" id="birth-day" min="1904-01-01" required>

                <hr>
                <button type="submit" class="registerbtn">Register</button>
            </form>
        </div>
    </form>
    <div class="container signin">
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
