 <%-- 
    Document   : list
    Created on : Jun 1, 2023, 10:26:42 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Dish Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <h1>Danh sách món ăn</h1>
      
        <%@include file="menu.jsp" %>
        
        <c:if test="${admin != null}">
            <a href="AddServlet">Add Dish</a>
        </c:if>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Information</th>
                    <th>Price</th>
                    <th>image</th>
                    <th>Comment</th>
                    <th>Type Of Dish</th>
                    <!--phân quyền chỉ dành cho admin-->
                     <c:if test="${admin == null}">
                    <th>Update</th>
                    <th>Delete</th>
                    </c:if>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dish" items="${list}">
                    <tr>
                        <td>${dish.getDish_id()}</td>
                        <td>${dish.getName()}</td>
                        <td>${dish.getInfor()}</td>
                        <td>${dish.getPrice()}</td>
                        
                        <td> 
                            <div class="h-24">
                                <img src="images/${dish.getImage()}" alt="image" class="max-h-2 rounded-lg"/>
                            </div>
                                
                           
                            
                        </td>
                        <td>${dish.getComment()}</td>
                        <td>${dish.getTypeOfDish()}</td>
                        <c:if test="${admin == null}">
                        <td style="text-align: center;">
                            <form action="updateServlet" method="GET">
                                <!--dung method GET-->
                                <input type="hidden" name="dish_id" value="${dish.getDish_id()}">
                                <button type="submit" name="updateButton" value="update" class="fancy-button" style="background-color: aquamarine; border: none; border-radius: 10px; padding: 5px 9px; cursor: pointer">
                                    <i class="fa-solid fa-wrench"></i>
                                </button>
                            </form>
                        </td>
                        <td style="text-align: center">
                            <form action="deleteServlet" method="POST">
                                <input type="hidden" name="dish_id" value="${dish.getDish_id()}">
                                <button type="submit" name="deleteButton" value="delete" class="fancy-button" style="background-color: burlywood; border: none; border-radius: 10px; padding: 5px 9px; cursor: pointer">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </form>
       
                        </td>
                        </c:if>
                    </tr>  
                </c:forEach>
            </tbody>
        </table>
        
        <!--Vì mình set scope là "session" nên biến "message" sẽ đc giữ 
        nguyên in ra mặc dù  đã truy cập nhiều Tab khác, muốn khi update 
        xong thì xóa dòng chữ "Updated!" đó thì phải set session đó lại về 
        null-->
        <h3>${message}</h3>
        <c:set var="message" value="${null}" scope="session"></c:set>
    </body>
</html>
