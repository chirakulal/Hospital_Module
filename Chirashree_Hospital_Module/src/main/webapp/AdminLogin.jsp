<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <!-- Bigger Logo -->
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png"
                     alt="Logo" width="70" height="64"
                     class="d-inline-block align-text-top me-2">
                <!-- Stylish Heading -->
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>

        </div>
    </nav>
</header>
<!-- Bootstrap background image section -->
<section class="bg-image d-flex justify-content-center align-items-center text-center"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                height: 100vh;">

    <form action="sendOtp" method="post">
        <div>
        <div class="mb-3">
            <div class="input-group mb-3">
                <span class="input-group-text">Email</span>
                <input type="email" class="form-control" name="email" placeholder="Admin Email" oninput="validateEmail()" onchange="checkEmail()" required>
                <button class="btn btn-success" type="submit">Send OTP</button>
            </div>
               <div id="emailError" class="form-text text-danger">
                <c:if test="${not empty message}">${message}</c:if>
            </div>
        </div>



        <!-- OTP Input -->
        <div class="input-group mb-3">
            <span class="input-group-text">OTP</span>
            <input type="text" class="form-control" id="otp" placeholder="Enter OTP">
        </div>

        <!-- Verify/Login Button -->
        <button class="btn btn-success w-100 fw-semibold mb-2" type="button" onclick="verifyOtp()">Login</button>

        <!-- Resend OTP Button with Timer -->
        <button class="btn btn-outline-success w-100 fw-semibold" id="resendOtpBtn" type="button" onclick="sendOtp()" disabled>
            Resend OTP (<span id="timer">120</span>s)
        </button>
    </div>
    </form>
</section>

<!-- Footer -->
<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed with using Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>
</body>
</html>
