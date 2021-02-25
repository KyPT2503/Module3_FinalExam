<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>Add New Product</h1>
    <form action="${pageContext.request.contextPath}/products?action=update&id=${product.getId()}" method="post"
          class="col-sm-12">
        <div class="form-group">
            <label>Name:
                <input type="text" class="form-control" name="name" value="${product.getName()}">
            </label>
        </div>
        <div class="form-group">
            <label>Price:
                <input type="number" class="form-control" name="price" value="${product.getPrice()}">
            </label>
        </div>
        <div class="form-group">
            <label>Quantify:
                <input type="number" class="form-control" name="amount" value="${product.getAmount()}">
            </label>
        </div>
        <div class="form-group">
            <label>Color:
                <input type="text" class="form-control" name="color" value="${product.getColor()}">
            </label>
        </div>
        <div class="form-group">
            <label for="description">Description</label><textarea name="description" id="description" cols="30"
                                                                  rows="10" class="form-control"
                                                                  placeholder="${product.getDescription()}"></textarea>
        </div>
        <div class="form-group">
            <h1>Category name: ${product.getCategory()}</h1>
            <label for="category">Category</label><select name="category" id="category" class="custom-select">
            <option
                    <c:if test="${product.getCategory().equals('Phone')}">selected</c:if> value="Phone" class="">Phone
            </option>
            <option
                    <c:if test="${product.getCategory().equals('Television')}">selected</c:if> value="Television">Television
            </option>
            <option
                    <c:if test="${product.getCategory().equals('Pen')}">selected</c:if> value="Pen">Pen
            </option>
            <option
                    <c:if test="${product.getCategory().equals('Book')}">selected</c:if> value="Book">Book
            </option>
            <option
                    <c:if test="${product.getCategory().equals('Laptop')}">selected</c:if> value="Laptop">Laptop
            </option>
        </select>
        </div>
        <button type="submit" class="btn btn-success">Create</button>
        | <a href="${pageContext.request.contextPath}/product/product.jsp">
        <button class="btn btn-dark">Back</button>
    </a>
    </form>
</div>
</body>
</html>
