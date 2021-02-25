<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <style>
        table{
            border-collapse: collapse;
        }
        th,td{
            border: 1px solid grey;
        }
    </style>
</head>
<body>
<br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <a href="${pageContext.request.contextPath}/product/product.jsp">
                <button class="btn btn-success">
                    <i class="fa fa-plus"></i> ADD NEW PRODUCT
                </button>
            </a>
        </div>
        <div class="col-sm-5"></div>
        <form action="${pageContext.request.contextPath}/products?action=search" method="post" class="col-sm-4">
            <div class="form-group">
                <label>
                    <input type="text" class="form-control" name="name">
                </label>
                <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i>Search</button>
            </div>
        </form>
    </div>
    <table class="col-sm-12">
        <tr>
            <th>#</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantify</th>
            <th>Color</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.getId()}</td>
                <td>${product.getName()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.getAmount()}</td>
                <td>${product.getColor()}</td>
                <td>${product.getCategory()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/products?action=update&id=${product.getId()}">
                        <button class="btn btn-success"><i class="fa fa-pencil"></i></button>
                    </a>|
                    <a href="${pageContext.request.contextPath}/products?action=remove&id=${product.getId()}">
                        <button class="btn btn-danger"><i class="fa fa-trash"></i></button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
