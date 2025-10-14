<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Verify OTP - Healing Hands Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png"
                     alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>
        </div>
    </nav>
</header>

<section class="bg-image d-flex justify-content-center align-items-center text-center"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                height: 100vh;">

    <div class="card shadow-lg p-4" style="width: 100%; max-width: 400px;">
        <h3 class="text-success text-center mb-4">Verify OTP</h3>

        <form action="verifyOtp" method="post">
            <input type="hidden" name="email" value="${param.email}"> <!-- Email passed from Send OTP -->

            <div class="mb-3">
                <label for="otp" class="form-label">Enter OTP</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-key-fill"></i></span>
                    <input type="text" class="form-control" id="otp" name="otp" placeholder="6-digit OTP" required>
                </div>
            </div>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="form-text text-danger mt-1">${error}</div>
            </c:if>

            <!-- Success Message -->
            <c:if test="${not empty success}">
                <div class="form-text text-success mt-1">${success}</div>
            </c:if>

            <button type="submit" class="btn btn-success w-100 fw-semibold mb-2">Login</button>
        </form>

        <form id="resendForm" action="resendOtp" method="post" class="w-100">
            <input type="hidden" name="email" value="${email}">
            <button id="resendOtpBtn" class="btn btn-outline-success w-100 fw-semibold" type="submit" disabled>
                Resend OTP (<span id="timer">${remainingSeconds}</span>s)
            </button>
        </form>
        <div class="text-center mt-2 text-muted">
            <small>You can resend the OTP after 2 minutes.</small>
        </div>
    </div>

</section>

<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<input type="hidden" name="email" value="${email}">

<script>
    window.addEventListener("DOMContentLoaded", () => {
        // Use the value passed from the backend controller
        let timeLeft = parseInt('${remainingSeconds}');
        const timerElement = document.getElementById("timer");
        const resendBtn = document.getElementById("resendOtpBtn");

        // Only start the countdown if there is time left
        if (timeLeft > 0) {
            const countdown = setInterval(() => {
                timeLeft--;
                timerElement.textContent = timeLeft;

                if (timeLeft <= 0) {
                    clearInterval(countdown);
                    resendBtn.disabled = false;
                    // Update button text to be cleaner
                    resendBtn.innerHTML = "Resend OTP";
                }
            }, 1000);
        } else {
            // If time is already 0, enable the button immediately
            resendBtn.disabled = false;
            resendBtn.innerHTML = "Resend OTP";
        }
    });
</script>
<script src="validation.js"></script>

</body>
</html>
