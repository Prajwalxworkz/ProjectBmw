<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin OTP Login</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav.css">
  <!-- Custom Style -->
  <style>
    body {
       background-image: url(<%=request.getContextPath()%>/resources/images/adminLoginBg3.jpg);
      background-size: cover;
    }

    .blur-container {
      background: rgba(255, 255, 255, 0.2);
      backdrop-filter: blur(3px);
      -webkit-backdrop-filter: blur(10px);
      border-radius: 10px;
      padding: 30px;
    }

    #timer {
      color: white;
      font-weight: bold;
      font-size: 18px;
    }
  </style>
</head>
<body>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath()%>">
        <img src="<%=request.getContextPath()%>/resources/images/bmw-motorrad-seeklogo.png" width="250px" alt="BMW Logo" />
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link active" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About Us</a>
          </li>
        </ul>
        <form class="d-flex">
          <a class="btn btn-light" href="adminLogin.jsp">
            <i class="bi bi-person fs-4"></i> &nbsp;Admin
          </a>
        </form>
      </div>
    </div>
  </nav>

  <!-- OTP Form Section -->
  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-md-4 blur-container text-white">
        <h2 class="text-center mb-4"><i>Admin Login</i></h2>

        <form action="verifyOtp" method="post">
          <div class="mb-3">
            <input class="form-control" type="text" name="otp" placeholder="Enter OTP" required>
          </div>
          <div class="mb-3 text-center">
            <button type="submit" class="btn btn-primary px-4">Submit</button>
          </div>
        </form>

        <!-- Timer Display -->
        <div class="text-center mt-3">
          <p id="timer">120 seconds remaining</p>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Countdown Timer Script -->
  <script>
    window.onload = function () {
      let timeRemaining = 120;
      const timerDisplay = document.getElementById('timer');

      function updateTimerDisplay() {
        timerDisplay.textContent = timeRemaining + " seconds remaining";
      }

      function countdown() {
        if (timeRemaining > 0) {
          timeRemaining--;
          updateTimerDisplay();
        } else {
          clearInterval(timerInterval);
          timerDisplay.textContent = "Time's up!";
        }
      }

      updateTimerDisplay();
      const timerInterval = setInterval(countdown, 1000);
    };
  </script>
</body>
</html>
