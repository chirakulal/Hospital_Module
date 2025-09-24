<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manage Specializations - Healing Hands</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png" alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success">Healing Hands Hospital</span>
            </a>
            <a href="dashboard-success" class="btn btn-outline-success fw-semibold">
                Back to Dashboard
            </a>
        </div>
    </nav>
</header>

<main class="bg-image d-flex align-items-center" style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png'); background-size: cover; background-position: center; min-height: 85vh; padding: 2rem 0;">

    <div class="container">
        <div class="card shadow-lg mx-auto" style="max-width: 700px; background: rgba(255, 255, 255, 0.95);">
            <div class="card-body p-5">

                <h2 class="card-title text-center text-success fw-bold mb-4">Manage Specializations</h2>

                <c:if test="${not empty message}">
                    <div class="alert alert-success text-center" role="alert">
                        ${message}
                    </div>
                </c:if>

                <form action="specializations" method="post">
                    <label for="specializationName" class="form-label fw-semibold">New Specialization Name</label>
                    <div class="input-group">
                        <input type="text" id="specializationName" name="name" class="form-control" placeholder="e.g., Cardiology" required>
                        <button type="submit" class="btn btn-success">Add Specialization</button>
                    </div>
                    <c:if test="${not empty errors.name}">
                        <div class="text-danger mt-1 small">${error}</div>
                    </c:if>

                    <c:if test="${not empty successMessage}">
                        <div class="text-success mt-1 small">${success}</div>
                    </c:if>

                </form>

            </div>
        </div>
    </div>
</main>

<footer class="bg-success text-white text-center py-3">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>