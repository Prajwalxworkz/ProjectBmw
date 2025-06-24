<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
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
    background-image: url(<%=request.getContextPath()%>/resources/images/bikeBg3.jpg);
    background-position: center;
    background-size: cover;
}
.blur-container {
  background: rgba(255, 255, 255, 0.2); /* semi-transparent */
  backdrop-filter: blur(3px);
  -webkit-backdrop-filter: blur(10px); /* for Safari */
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
        <a class="navbar-brand" href="<%=request.getContextPath()%>"
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
              ><i class="bi bi-person-check fs-4"></i> &nbsp;Admin</a
            >
          </form>
        </div>
      </div>
    </nav>
    <!-- --------------------------------Modal----------------------------------- -->

       <c:if test="${not empty AssignBikeToBranchErrorMsg}">
           <button type="button" class="d-none" data-bs-toggle="modal" data-bs-target="#errorModal" id="autoClick"></button>

           <script>
               document.addEventListener("DOMContentLoaded", function() {
                   document.getElementById("autoClick").click();
               });
           </script>
       </c:if>

       <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
         <div class="modal-dialog">
           <div class="modal-content">

             <!-- Modal Header -->
             <div class="modal-header">
               <h5 class="modal-title" id="errorModalLabel">Error</h5>
               <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
             </div>

             <!-- Modal Body -->
             <div class="alert alert-danger">
               <p >${AssignBikeToBranchErrorMsg}</p>
             </div>

           </div>
         </div>
       </div>

  <!-- --------------------------------Form----------------------------------- -->
<div class="container my-5 blur-container">
<div class="text-center h4">* All fields are mandatory</div>
  <form action="addBike" method="post" enctype="multipart/form-data">
    <div class="row justify-content-center">
      <!-- Left Column -->
      <div class="col-md-4">
        <div class="mb-3">
          <input class="form-control" type="text" name="modelName" placeholder="Model Name" required />
        </div>
        <div class="mb-3">
          <input class="form-control" type="text" name="topSpeed" placeholder="Top Speed" required/>
        </div>
        <div class="mb-3">
          <input class="form-control" type="text" name="mileage" placeholder="Mileage" required/>
        </div>
        <div class="mb-3">
          <input class="form-control" type="text" name="engineCapacity" placeholder="Engine Capacity" required/>
        </div>
        <div class="mb-3">
          <input class="form-control" type="text" name="transmission" placeholder="Transmission" required />
        </div>
        <div class="mb-3">
          <input class="form-control" type="text" name="kerbWeight" placeholder="Kerb Weight" required/>
        </div>
      </div>

      <!-- Right Column -->
      <div class="col-md-4">
        <div class="mb-3">
          <input class="form-control" type="text" name="fuelTankCapacity" placeholder="Fuel Tank Capacity" required />
        </div><div class="mb-3">
          <input class="form-control" type="text" name="exShowroomPrice" placeholder="Ex-Showroom Price" required />
        </div>
        <div class="mb-3">
          <input class="form-control" type="file" name="multipartFile1" placeholder="Image 1" />
        </div>
        <div class="mb-3">
          <input class="form-control" type="file" name="multipartFile2" placeholder="Image 2" />
        </div>
        <div class="mb-3">
          <input class="form-control" type="file" name="multipartFile3" placeholder="Image 3" />
        </div>
        <div class="mb-3">
          <input class="form-control" type="file" name="multipartFile4" placeholder="Image 4" />
        </div>
          <p style="color: red;">${addBikeErrorMsg}</p>
        </div>
    </div>

    <!-- Submit Button -->
    <div class="row">
      <div class="col text-center">
        <button class="btn btn-primary px-5" type="submit">Submit</button>
      </div>
    </div>
  </form>
</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>