<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
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

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <html lang="en">
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
    <section class="bg-image d-flex justify-content-center align-items-start"
             style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh; padding-top: 40px;">
        <div class="card shadow-lg p-4" style="width: 90%; max-width: 600px; background: rgba(255,255,255,0.95);">
            <h3 class="text-success mb-3 text-center">Doctor Personal Information</h3>

            <!-- Doctor Form -->
            <form action="saveDoctor" method="post">
                <div class="row g-3">
                    <!-- First Name -->
                    <div class="col-md-6">
                        <label for="firstName" class="form-label fw-semibold">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" required>
                    </div>

                    <!-- Last Name -->
                    <div class="col-md-6">
                        <label for="lastName" class="form-label fw-semibold">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" required>
                    </div>

                    <!-- Email -->
                    <div class="col-md-6">
                        <label for="email" class="form-label fw-semibold">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="doctor@example.com" required>
                    </div>

                    <!-- Phone -->
                    <div class="col-md-6">
                        <label for="phone" class="form-label fw-semibold">Phone Number</label>
                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter phone number" required>
                    </div>

                    <!-- Specialization -->
                    <div class="col-md-6">
                        <label for="specialization" class="form-label fw-semibold">Specialization</label>
                        <select class="form-select" id="specialization" name="specialization" required>
                            <option value="">Choose...</option>
                            <option>Cardiologist</option>
                            <option>Neurologist</option>
                            <option>Orthopedic</option>
                            <option>Dermatologist</option>
                            <option>Pediatrician</option>
                            <option>General Physician</option>
                        </select>
                    </div>

                    <!-- Experience -->
                    <div class="col-md-6">
                        <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
                        <input type="number" class="form-control" id="experience" name="experience" min="0" max="50" required>
                    </div>

                    <!-- Address -->
                    <div class="col-12">
                        <label for="address" class="form-label fw-semibold">Address</label>
                        <textarea class="form-control" id="address" name="address" rows="2" placeholder="Enter full address" required></textarea>
                    </div>

                    <!-- Gender -->
                    <div class="col-12">
                        <label class="form-label fw-semibold">Gender</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="Male" required>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="Female">
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </div>

                    <!-- Submit -->
                    <div class="col-12 text-center">
                        <button type="submit" class="btn btn-success px-5 mt-3">Save Details</button>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-success text-white text-center py-3 mt-auto">
        <div class="container">
            <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
            <small>Designed using Bootstrap</small>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    <script src="validation.js"></script>

    </body>
    </html>

</header>

<!-- Main Section -->
<section class="bg-image d-flex justify-content-center align-items-center text-center"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                height: 100vh;">


</section>

<!-- Footer -->
<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>

</body>
</html>
