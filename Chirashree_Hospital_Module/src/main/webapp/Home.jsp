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
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link fw-semibold text-success" href="admin">Home</a>
                </li>
            </ul>
        </div>
    </nav>


</header>
<!-- Bootstrap background image section -->
<!-- Bootstrap background image section -->
<!-- Bootstrap background image section -->
<section class="bg-image d-flex"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh; margin: 0;">

    <!-- Sidebar Card -->
    <div class="card shadow-lg p-4 rounded-0 border-0"
         style="width: 300px; height: 100vh; background: rgba(255,255,255,0.9);">
        <h3 class="text-success mb-3">Admin Dashboard</h3>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link text-success fw-bold" href="doctors">Doctor Details</a>
            </li>
            <!-- Uncomment more items if needed -->
        </ul>
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
</body>
</html>
