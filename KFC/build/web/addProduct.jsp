<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <h1>Add Dish</h1>
        <form action="addServlet" method="POST"enctype="multipart/form-data">

            <!-- Mã HTML cho biểu mẫu -->
            Name: <input type="text" name="name" required><br>
            Information: <input type="text" name="infor" required><br>
            Price: <input type="number" name="price" required><br>
            Image: <input type="file" name="image" accept="image/*" required><br>
            Comment: <input type="text" name="comment" ><br>
            Type of dish: <input type="text" name="typeOfDish" required><br>
            <input type="submit" value="Submit!" name="submit" />
        </form>
    </body>
</html>