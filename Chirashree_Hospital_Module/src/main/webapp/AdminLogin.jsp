<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
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

    <div class="card p-4 shadow-lg bg-white bg-opacity-75" style="max-width: 400px; width: 100%;">
        <h4 class="mb-3 text-success fw-bold">Admin Login</h4>

        <!-- Email Input with Send OTP -->
        <div class="input-group mb-3">
            <span class="input-group-text">Email</span>
            <input type="email" class="form-control" id="email" placeholder="Admin Email" aria-label="Admin Email" onchange="CheckEmail()"  oninput="validateEmail()" required>
            <button class="btn btn-success" type="button">Send OTP</button>
            <div id="emailError" class="form-text text-danger"></div>

        </div>

        <!-- OTP Input (shown after sending OTP) -->
        <div class="input-group mb-3">
            <span class="input-group-text">OTP</span>
            <input type="text" class="form-control" placeholder="Enter OTP" aria-label="OTP">
        </div>

        <!-- Login Button -->
        <button class="btn btn-success w-100 fw-semibold" type="button">Login</button>
    </div>
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
