<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CarValue Request page</title>
</head>
<body>
<h1><%= "Welcome to our request page" %></h1>
    <br>
    <form name="requestForm" method="post" action="/Requester">
        <h3>Personal Details</h3>
        Name: <input type="text" name="username"><br>
        Date of Birth: <input type="date" name="birth_date"><br>
        Country: <input type="text" name="Country"><br>
        Preferred language:<br>
            <input type="radio" id="en" name="language" value="en">
        <label for="en">English</label><br>
        <input type="radio" id="hu" name="language" value="hu">
            <label for="en">Magyar</label><br>

        <h3>Car Details</h3>
        Brand: <input type="text" name="carBrand"><br>
        Type: <input type="text" name="carType"><br>
        Plate Number: <input type="text" name="carPlate"><br>
        Year of manufacture: <input type="number" name="carYear"><br>
        Driven distance <input type="number" name="carDistance"><br>
    </form>
</h1>
<br/>
<a href="Response">Hello Servlet</a>
</body>
</html>