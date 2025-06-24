<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous"
  />
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
  />
   <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav.css">
  <style>
body{
    background-image: url(<%=request.getContextPath()%>/resources/images/adminLoginBg3.jpg);
     background-size: cover;
}
.blur-container {
  background: rgba(255, 255, 255, 0.9); /* semi-transparent */
  backdrop-filter: blur(3px);
  -webkit-backdrop-filter: blur(5px); /* for Safari */
  border-radius: 10px;
  padding: 20px;
}
  </style>
</head>
<body>
  <!-- --------------------------------Navbar-------------------------------------- -->

    <nav
      class="navbar navbar-expand-lg navbar-light bg-light"
      style="position: sticky; top: 0; z-index: 1000;"
    >
      <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/"
          > <img src="<%=request.getContextPath()%>/resources/images/bmw-motorrad-seeklogo.png" width="250px" alt="..." /></a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About Us</a>
            </li>
          </ul>
          <form class="d-flex">
            <!-- <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"> -->
            <!-- <button class="btn btn-outline-success" type="submit"><i class="bi bi-search"></i></button> -->
             <a class="btn btn-light" href="<%=request.getContextPath()%>/admin/adminLogin.jsp"
              ><i class="bi bi-person fs-4"></i> &nbsp;Admin</a
            >
          </form>
        </div>
      </div>
    </nav>

  <!-- --------------------------------Table----------------------------------- -->
<div class="container my-5 blur-container">
<div class="text-center">
    <p style="color: red;">${followUpErrorMsg}</p>
</div>
 <div class="table-responsive">
   <table class="table table-success table-striped border-dark table-bordered caption-top align-middle ">
     <caption style="color:#000;"><h3>Reason Update</h3></caption>
     <thead>
       <tr class="align-middle">
         <th scope="col">Name</th>
         <th scope="col">Ph number</th>
         <th scope="col">Schedule</th>
         <th scope="col">DateTime</th>
         <th scope="col">Showroom</th>
         <th scope="col">Model Name</th>
         <th scope="col">Reason</th>
         <th scope="col">Updated DateTime</th>
         <th scope="col">Action</th>

       </tr>
     </thead>
    <tbody>
        <tr>
         <td>${customer.getFullName()}</td>
         <td>${customer.getPhoneNumber()}</td>
         <td>${customer.getSchedule()}</td>
         <td>${customer.getDateAndTime()}</td>
         <td>${customer.getBranchName()}</td>
         <td>${customer.getModelName()}</td>
         <form action="followUp" method="post">
         <td>
            <input class="form-control" type="text" name="reason"/>
         </td>
         <td>
            <input class="form-control" type="datetime-local" name="updatedDateTime"/>
         </td>
             <input class="form-control visually-hidden" type="text" name="customer_id" value="${customer.getId()}"/>
         <td>
             <button class="btn btn-primary" type="submit" >Submit</button>
         </td>
         </form>
        </tr>
    </tbody>
   </table>
 </div>
  <!-- --------------------------------End-------------------------------------->

    <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"
  ></script>
</body>
</html>