<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Dish Information</title>
</head>
<body>
    <h1>Update Dish Information</h1>

    <form action="updateServlet" method="post" enctype="multipart/form-data">
         <input type="hidden" name="dish_id" value="${dish.getDish_id()}">
        
        <label for="name">Name:</label>
        <input  type="text" id="name" name="name"value="${dish.getName()}" ><br><br>

        <label for="infor">Information: </label>
        <textarea value="${dish.getInfor()}" id="infor" name="infor" rows="4" cols="50" ></textarea><br><br>

        <label for="price">Price: </label>
        <input value="${dish.getPrice()}"type="number" id="price" name="price" ><br><br>

        <label for="image">Image: </label>
        <input value="${dish.getImage()} "type="file" id="image" name="image" ><br><br>

        <label for="comment">Comment</label>
        <textarea  value="${dish.getComment()} "id="comment" name="comment" rows="4" cols="50" ></textarea><br><br>

        <label for="typeOfDish">Type Of Dish: </label>
        <input value="${dish.getTypeOfDish()}"type="text" id="typeOfDish" name="typeOfDish" ><br><br>

        <input type="submit" value="update">
    </form>
</body>
</html>



