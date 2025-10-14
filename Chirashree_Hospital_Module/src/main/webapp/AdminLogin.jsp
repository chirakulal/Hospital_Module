<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Healing Hands Hospital</title>
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

<!-- Main Section -->
<section class="bg-image d-flex justify-content-center align-items-center text-center"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                height: 100vh;">

    <div class="card shadow-lg p-4" style="width: 100%; max-width: 400px;">
        <h3 class="text-success text-center mb-4">Admin Login</h3>

        <form action="sendOtp" method="post">
            <div class="mb-3">
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="email"
                           placeholder="Enter your email" oninput="validateEmail()" onchange="checkEmail()" value="${dto.email}" required>

                </div>

                <div id="messageArea">
                    <c:if test="${not empty emailError}">
                        <div class="form-text text-danger mt-1">${emailError}</div>
                    </c:if>
                    <c:if test="${not empty success}">
                        <div class="form-text text-success mt-1">${success}</div>
                    </c:if>
                </div>
            </div>



            <button type="submit" class="btn btn-success w-100 fw-semibold">Send OTP</button>
        </form>

        <div class="text-center mt-3 text-muted">
            <small>You will receive a 6-digit OTP via email.</small>
        </div>
    </div>

</section>

<!-- Footer -->
<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>
<script>

    const messageArea = document.getElementById("messageArea");
    const emailField = document.getElementById("email");

    function validateEmail(){
        let email = emailField.value;
        let pattern = /^[A-Za-z0-9._%+-]+@gmail\.com$/;


        messageArea.innerHTML = "";
        emailField.setCustomValidity("");

        if(!pattern.test(email)){

            messageArea.innerHTML = '<div class="form-text text-danger mt-1">Please enter valid email (eg: user@gmail.com)</div>';


            emailField.setCustomValidity("Invalid email format.");
        }

    }

    function checkEmail(){
        let email = emailField.value;


        validateEmail();


        if (emailField.checkValidity() === false) {
             return;
        }


        const xhttp = new XMLHttpRequest();

        xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/checkEmail/"+email, true);
        xhttp.send();

        xhttp.onload = function(){

            messageArea.innerHTML = "";


            if (this.responseText.trim() !== "") {

                messageArea.innerHTML = '<div class="form-text text-danger mt-1">' + this.responseText + '</div>';
            }
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
</body>
</html>