<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Healing Hands Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
        body { margin: 0; overflow-x: hidden; }
    </style>
</head>
<body>

<!-- Offcanvas Navbar -->
<nav class="navbar bg-body-tertiary fixed-top shadow-sm" style="background-color: var(--bs-success-bg-subtle) !important;">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="#">
            <img src="/Chirashree_Hospital_Module/resources/images/logo.png" alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
            <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title text-success" id="offcanvasNavbarLabel">Admin Dashboard</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-success" href="doctor">Doctor Details</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-success" href="AddSpecialization">AddSpecialisation</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-success" href="slot">Slot</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-success" href="addslot">AddSlot</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold text-success" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<!-- Content Section -->
<section class="bg-image d-flex"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: calc(100vh - 150px);
                margin-top: 80px;"> <!-- margin-top added for fixed navbar -->

    <div class="container mt-5">
        <h1 class="text-success">Welcome to the Admin Dashboard!</h1>
        <p>You have successfully logged in.</p>
    </div>
</section>

<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed with Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
</body>
</html>
