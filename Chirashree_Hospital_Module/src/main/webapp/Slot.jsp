<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Healing Hands Hospital</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<!-- Header -->
<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid d-flex justify-content-between align-items-center">

            <!-- Logo + Title -->
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png" alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>

            <!-- Back Button -->
            <a href="dashboard-success" class="btn btn-outline-success fw-semibold">
                Back to Dashboard
            </a>
        </div>
    </nav>
</header>

<!-- Main Section -->
<section class="bg-image d-flex justify-content-center align-items-start py-5"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh;">
    <div class="card shadow-lg p-4 w-100"
         style="max-width: 700px; background: rgba(255,255,255,0.95); border-radius: 12px;">

        <div class="container">
            <h2 class="mb-4 text-center text-success fw-bold">Submit a Time Slot Range</h2>

            <!-- Time Slot Form -->
            <form action="saveTime" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="startTime" class="form-label fw-semibold">Start Time:</label>
                        <input type="time" class="form-control shadow-sm" id="startTime" name="startTime" required>
                    </div>
                    <div class="col-md-6">
                        <label for="endTime" class="form-label fw-semibold">End Time:</label>
                        <input type="time" class="form-control shadow-sm" id="endTime" name="endTime" required>
                    </div>
                </div>

                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-success btn-lg shadow-sm">
                        Post Time to Database
                    </button>
                </div>

                <!-- Error / Success Messages -->
                <c:choose>
                    <c:when test="${not empty error}">
                        <div class="alert alert-danger mt-3 text-center shadow-sm">${error}</div>
                    </c:when>
                    <c:when test="${not empty success}">
                        <div class="alert alert-success mt-3 text-center shadow-sm">${success}</div>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="bg-success text-white text-center py-3 mt-auto shadow-sm">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>
</body>
</html>
