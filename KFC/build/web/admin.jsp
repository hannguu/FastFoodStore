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
        <link rel="stylesheet" href="css/styleadmin.css"/>
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Danh sách món ăn</h1>
      
        <%@include file="menu.jsp" %>
        
        <c:if test="${admin != null}">
            <a href="AddServlet">Add Dish</a>
        </c:if>

            <form action="ListProductServlet" method="GET" >
                
        
            <div class="container">
                <div class="row">
                    <c:forEach var="dish" items="${list}">
                <div class="product col-md-3 mx-3 mb-4">
                    <img><img src="img/${dish.getImage()}" alt="alt"/>
                    <div class="product-name-price">
                        <h3 class="product-name">${dish.getName()}</h3>
                        <p class="product-price">${dish.getPrice()}</p>
                    </div>
                    
                    <p class="product-info">${dish.getInfor()}</p>
                    
                    <div class="product-button d-flex justify-content-center">
                        <button class="product-edit btn btn-secondary"><i class="fa-solid fa-wrench"></i></button>
                        <button class="product-delete btn btn-danger"> <i class="fa-solid fa-trash"></i></button>
                    </div>
                </div>
                </c:forEach>
        
               </div>
            </div>
                    </form>
        <h3>${message}</h3>
        <c:set var="message" value="${null}" scope="session"></c:set>
    </body>
</html>