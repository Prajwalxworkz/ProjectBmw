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
    background-image: url(<%=request.getContextPath()%>/resources/images/customerRegBg4.jpg);
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
  <script>
    function updateButtonText(){
        let schedule=document.getElementById("schedule").value;
        let submitBtn=document.getElementById("submitBtn");

        if(schedule==="Booking"){
            submitBtn.textContent="Booking";
        }else if(schedule==="Test_Ride"){
            submitBtn.textContent="Test Ride";
        }else{
             submitBtn.textContent="Submit";
        }
    }
  </script>
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
<div class="container my-5 ">
  <form action="addCustomer" method="post" enctype="multipart/form-data">
    <div class="row justify-content-center gx- ">
      <!-- Left Column -->
      <div class="col-md-4 blur-container ">
<!-- Full Name -->
    <div class="mb-3">
      <input class="form-control" type="text" name="fullName" placeholder="Full Name"
             value="${customer.getFullName()}" required/>
      <c:if test="${not empty nameErrorMsg}">
        <small class="text-danger">${nameErrorMsg}</small>
      </c:if>
    </div>

    <!-- Email -->
    <div class="mb-3">
      <input class="form-control" type="email" name="email" placeholder="Email"
             value="${customer.getEmail()}"  />
      <c:if test="${not empty emailErrorMsg}">
        <small class="text-danger">${emailErrorMsg}</small>
      </c:if>
    </div>

    <!-- DTO Date -->
    <div class="mb-3">
      <input class="form-control" type="date" name="dto" placeholder="DTO"
             value="${customer.getDto()}" required/>
      <c:if test="${not empty dobErrorMsg}">
        <small class="text-danger">${dobErrorMsg}</small>
      </c:if>
    </div>

    <!-- Address -->
    <div class="mb-3">
      <input class="form-control" type="text" name="address" placeholder="Address"
             value="${customer.getAddress()}" />
      <c:if test="${not empty addressErrorMsg}">
        <small class="text-danger">${addressErrorMsg}</small>
      </c:if>
    </div>

    <!-- Phone -->
    <div class="mb-3">
      <input class="form-control" type="text" name="phoneNumber" placeholder="Phone"
             value="${customer.getPhoneNumber()}" />
      <c:if test="${not empty phNumberErrorMsg}">
        <small class="text-danger">${phNumberErrorMsg}</small>
      </c:if>
    </div>

    <!-- DL -->
    <div class="mb-3">
      <input class="form-control" type="text" name="drivingLicense" placeholder="Driving License"
             value="${customer.getDrivingLicense()}" />
      <c:if test="${not empty dlErrorMsg}">
        <small class="text-danger">${dlErrorMsg}</small>
      </c:if>
    </div>
      </div>

      <!-- Right Column -->
      <div class="col-md-4 blur-container ms-3">
        <div class="mb-3">
          <select class="form-select" id="schedule" name="schedule" onchange="updateButtonText()">
          <option value="">Select Schedule</option>
          <c:forEach items="${scheduleList}" var="schedule">
            <option value="${schedule}" ${schedule == customer.getSchedule() ? 'selected' : ''}>${schedule}</option>
           </c:forEach>
          </select>
           <c:if test="${not empty scheduleErrorMsg}">
                  <small class="text-danger">${scheduleErrorMsg}</small>
           </c:if>
        </div>
        <div class="mb-3">
          <input class="form-control" type="datetime-local" name="dateAndTime" placeholder="Date And Time"  value="${customer.getDateAndTime()}"/>
          <c:if test="${not empty dateTimeErrorMsg}">
             <small class="text-danger">${dateTimeErrorMsg}</small>
          </c:if>
        </div>
        <div class="mb-3">
            <select class="form-select" name="branchName">
            <option value="">Select Branch</option>
            <c:forEach items="${branchList}" var="branch">
              <option value="${branch.getBranchName()}" ${branch.getBranchName() == customer.getBranchName() ? 'selected' : ''}>${branch.getBranchName()}</option>
             </c:forEach>
            </select>
             <c:if test="${not empty branchNameErrorMsg}">
                    <small class="text-danger">${branchNameErrorMsg}</small>
             </c:if>
        </div>
       <input type="hidden" id="bikeIdField" name="bikeId" />
       <div class="mb-3">
       <select class="form-select" name="modelName" id="modelSelect" onchange="updateBikeId()">
           <option value="">Select Bike</option>
           <c:forEach items="${bikeList}" var="bike">
               <option value="${bike.getModelName()}" data-id="${bike.getId()}"
                   <c:if test="${bike.getModelName() == customer.getModelName()}">selected</c:if>>
                   ${bike.getModelName()}
               </option>
           </c:forEach>
       </select>
        <c:if test="${not empty modelNameErrorMsg}">
             <small class="text-danger">${modelNameErrorMsg}</small>
        </c:if>
      </div>
    <c:if test="${not empty saveErrorMsg}">
        <div class ="alert alert-danger">
            ${saveErrorMsg}
        </div>
    </c:if>

      </div>
    </div>

    <!-- Submit Button -->
    <div class="row">
      <div class="col text-center">
        <button class="btn btn-primary px-5" id="submitBtn" type="submit">Submit</button>
      </div>
    </div>
  </form>
</div>

<script>
  function updateBikeId() {
    const select = document.getElementById("modelSelect");
    const selectedOption = select.options[select.selectedIndex];
    const bikeId = selectedOption.getAttribute("data-id");
    document.getElementById("bikeIdField").value = bikeId || "";
  }

  // Run once on page load to pre-fill if needed
  document.addEventListener("DOMContentLoaded", updateBikeId);
</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>