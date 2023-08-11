<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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
<!-- Header-->
<%@ include file="header.jsp" %>

<section>
    <div id="search" class="text-black-left">
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
    </div>
    <div>
        <c:if test="${requestScope.films!=null}">
            <c:forEach items="${requestScope.films}"
                       var="film" varStatus="Loop">
                <div class="filmContainerItem  ">
                    <img src="${film.image}"
                         width="200" height="300">
                    <div class="text-black text-center sz-5-2" >
                        <p class="fa-2x" > ${film.title} </p><br>
                            ${film.description}<br><br>
                        Rate: ${film.rate} <br>
                        <c:set var="ratesList" value="${film.ratesList}" />
                        Numbers of votes: ${fn:length(ratesList)}<br>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span> <br>
                        <a class="py-5" href="${pageContext.request.contextPath}/film?id=${film.id}">Get more information </a>
                    </div>


                </div>

            </c:forEach>
        </c:if>
    </div>


</section>

<!-- Footer-->
<%@ include file="footer.jsp" %>


</body>
</html>
