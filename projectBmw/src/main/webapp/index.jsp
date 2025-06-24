<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
  </head>
  <body>
    <!-- --------------------------------Navbar-------------------------------------- -->
<div id="nav1">
  <nav class="navbar navbar-expand-lg navbar-light bg-light" style="position: sticky; top: 0; z-index: 1000;">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/">
        <img src="<%=request.getContextPath()%>/resources/images/bmw-motorrad-seeklogo.png" width="250px" alt="BMW Logo">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
        <a class="btn btn-light" href="<%=request.getContextPath()%>/admin/adminLogin.jsp">
            <i class="bi bi-person fs-4"></i> &nbsp;Admin
          </a>
        </form>
      </div>
    </div>
  </nav>
</div>
    <!-- --------------------------------Carousel-------------------------------------- -->
     <div class="d-flex">
    <div
      class=" border border-primary p-3 my-2 bg-dark"
      style="width: 100%;"
    >
      <div
        id="carouselExampleDark"
        class="carousel carousel-dark slide"
        data-bs-ride="carousel"
      >
        <div class="carousel-indicators">
          <button
            type="button"
            data-bs-target="#carouselExampleDark"
            data-bs-slide-to="0"
            class="active"
            aria-current="true"
            aria-label="Slide 1"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleDark"
            data-bs-slide-to="1"
            aria-label="Slide 2"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleDark"
            data-bs-slide-to="2"
            aria-label="Slide 3"
          ></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active" data-bs-interval="10000">
            <video width="100%" autoplay loop muted>
              <source src="<%=request.getContextPath()%>/resources/videos/BMW S1000RR CINEMATIC _ 4K - Trim.mp4" type="video/mp4" />
            </video>
            <!-- <div class="carousel-caption d-none d-md-block text-white text-uppercase">
              <h5>big boxer. bold style </h5>
              <p>
               the bmw r20 concept
              </p>
            </div> -->
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="<%=request.getContextPath()%>/resources/images/R20.png" class="d-block w-100" alt="..." />
            <div class="carousel-caption d-none d-md-block text-white text-uppercase">
              <h3>big boxer. bold style </h3>
              <p>
                the bmw r20 concept
              </p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="<%=request.getContextPath()%>/resources/images/f900gs.png" class="d-block w-100" alt="..." />
            <div class="carousel-caption d-none d-md-block text-white text-uppercase">
              <h3>no road?. no problem </h3>
              <p>
               bmw f900 gs
              </p>
            </div>
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleDark"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleDark"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>

  </div>
  <!-- --------------------------------Navbar2-------------------------------------- -->
<div id="nav2" class="container-fluid   my-3">
  <ul class="nav  justify-content-center align-items-center ">
    <li class="nav-item">
      <a class="nav-link active" aria-current="page" href="#"><i class="bi bi-grid-fill"></i>&nbsp;&nbsp;Model Overview</a>
    </li>
    <!-- <li class="nav-item">
      <a class="nav-link" href="#"><img src="<%=request.getContextPath()%>/resources/images/bike2.svg" alt="Sports Bike Icon" width="30" height="30">
        Demo ride</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><i class="bi bi-scooter"></i>&nbsp;&nbsp;Demo ride</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><i class="bi bi-calendar"></i>&nbsp;&nbsp;Booking</a>
    </li>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><i class="bi bi-tools"></i>&nbsp;&nbsp;Service</a>
    </li>-->
     <li class="nav-item">
          <a class="nav-link" href="#"><i class="bi bi-person fs-4 "></i>&nbsp;&nbsp;User</a>
     </li>
  </ul>
</div>
<!-- ----------------------------------------Travel------------------------------------------- -->
<div class="container my-5">
<figure class="text-center">
  <blockquote class="blockquote ">
    <h1 class="display-1"><b>Travel.</b></h1>
  </blockquote>
  <figcaption class="blockquote-footer">
    <small><b>Simply on the move - worldwide.</b></small>
  </figcaption>
</figure>
<img src="<%=request.getContextPath()%>/resources/images/gallery.jpg " width="100%" alt="...">
</div>

    <!-- --------------------------------Card----------------------------------- -->
    <!-- <div class="container my-5">
      <div class="row">
        <div class="col-sm-4">
          <div class="card border border-primary p-3" style="width: 18rem">
            <img
              src="images/R20.png"
              width="90px"
              class="card-img-top"
              alt="..."
            />
            <div class="card-body">
              <p class="card-text">
                Some quick example text to build on the card title and make up
                the bulk of the card's content.
              </p>
              <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill fs-2"></i></a>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="card border border-primary p-3" style="width: 18rem">
            <img src="images/bmw2.png" class="card-img-top" alt="..." />
            <div class="card-body">
              <p class="card-text">
                Some quick example text to build on the card title and make up
                the bulk of the card's content.
              </p>
              <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill fs-2"></i></a>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="card border border-primary p-3" style="width: 18rem">
            <img src="images/bmw3.png" class="card-img-top" alt="..." />
            <div class="card-body">
              <p class="card-text">
                Some quick example text to build on the card title and make up
                the bulk of the card's content.
              </p>
              <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill fs-2"></i></a>
            </div>
          </div>
        </div>
      </div>
    </div> -->

    <!-- --------------------------------Card Carousel-------------------------------------- -->
    <div class="container my-5 p-4 bg-dark">
      <div id="cardCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <!-- Slide 1 -->
          <div class="carousel-item active">
            <div class="d-flex justify-content-center gap-4">
              <!-- Cards -->
              <div class="card" style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwM1000RR.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">For all those driven by their passion and ready to take on challenges. For all those who refuse to give up one single millimeter or millisecond: The BMW M 1000 RR is the superbike homologated for racing. It was perfected in the wind tunnel and further developed on the circuit.</p>
                  <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
              <div class="card " style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwG310RR.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">The design of the BMW G 310 RR is characterised by motorsport: for a sporty appearance. The agile single-cylinder engine, Ride by Wire, the anti-hopping clutch and the various riding modes are designed for sheer riding pleasure. The BMW G 310 RR clearly showcases its S 1000 RR heritage.</p>
                  <a href="#" class="btn btn-primary "><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
              <div class="card " style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwF900GSA.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">This GS is your ideal touring companion. Tour on the road with our optimum dynamics and comfort. These highlights make the F 900 GS Adventure suitable for touring and make you ready for all short and long trips.</p>
                  <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
            </div>
          </div>

          <!-- Slide 2 -->
          <div class="carousel-item">
            <div class="d-flex justify-content-center gap-4">
              <div class="card " style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwR1300GSA.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">The BMW R 1300 GS Adventure is ready to conquer off-road with MSR (Dynamic Engine Brake Control), DTC (Dynamic Traction Control), Hill start control pro, M lightweight battery and so much more. </p>
                  <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
              <div class="card " style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwF900GS.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">The F 900 GS engine, the body – we have optimised everything to achieve a bike with maximum power but at a light weight. And here it is: your perfect Enduro.</p>
                  <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
              <div class="card " style="width: 18rem;">
                <img src="<%=request.getContextPath()%>/resources/images/bmwK1600GTL.png" class="card-img-top" alt="..." />
                <div class="card-body">
                  <p class="card-text">The BMW K 1600 GTL offers performance touring that leaves no wishes unfulfilled. Its powerful six-cylinder engine now delivers even more dynamism and superior character: thanks to the comfortably designed vehicle geometry, you can enjoy every kilometre.</p>
                  <a href="#" class="btn btn-primary"><i class="bi bi-arrow-right-circle-fill "></i></a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Carousel controls -->
        <button class="carousel-control-prev" type="button" data-bs-target="#cardCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#cardCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>
    </div>

  <!-- --------------------------------End-------------------------------------->


    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
