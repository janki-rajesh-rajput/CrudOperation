<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>
</head>
<body>
<header style="color:white">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Customer Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Customer</a>
        </li> 
      </ul>
    </div>
  </div>
</nav>
</header>

<br/>
<br/>

<div class="container" style="width:50%">

<c:if test="${customer == null}">

<form action="add" method="post">

<h2>Add customer</h2>

</c:if>
<c:if test="${customer != null}">

<form action="edit" method="post">

<h2>Edit customer</h2>

</c:if>

<div class="mb-3" hidden>
  <input value="<c:out value="${customer.id}"></c:out>" type="text" class="form-control" id="exampleFormControlInput" name="tbid" >
</div>

<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Name</label><br>
  <input type="text" value="<c:out value="${customer.name}"></c:out>" class="form-control" id="exampleFormControlInput1" name="tbname" placeholder="Enter your name" required="required">
</div>

<div class="mb-3">
  <label for="exampleFormControlInput2" class="form-label">Email address</label><br>
  <input type="email" value="<c:out value="${customer.email}"></c:out>" class="form-control" id="exampleFormControlInput2" name="tbemail" placeholder="Enter your email" required="required">
</div>


<div class="mb-3">
  <label for="exampleFormControlInput3" class="form-label">Mobile</label><br>
  <input type="tel" value="<c:out value="${customer.mobail}"></c:out>" class="form-control" id="exampleFormControlInput3" name="tbmobail" placeholder="Enter your mobail no" required="required">
</div>

<div>
<button type="submit" class="btn btn-success">Save</button>
</div>
</form>
</div>
</body>
</html>