<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Healing Hands Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
</head>
<body>

<!-- Header (kept same as your request) -->
<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png"
                     alt="Logo" width="70" height="64"
                     class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>
            <div class="d-flex ms-auto">
                <a href="updateDetails" class="btn btn-outline-success fw-semibold me-2">Update Details</a>
                <a href="dashboard-success" class="btn btn-outline-success fw-semibold">Back to Dashboard</a>
            </div>
        </div>
    </nav>
</header><!-- Section -->
<section class="bg-image d-flex justify-content-center align-items-start"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh; padding-top: 40px;">
    <div class="card shadow-lg p-4 w-100"
         style="max-width: 800px; background: rgba(255,255,255,0.95); border-radius: 12px;">

<div class="container mt-5">
    <div class="card shadow p-4 rounded-4">
        <h3 class="mb-4 text-center">Patient Registration Form</h3>
        <c:if test="${not empty errors}">
            <div class="alert alert-danger text-center shadow-sm">
                <c:forEach var="err" items="${errors}" varStatus="status">
                    <c:if test="${status.first or not fn:contains(previousErrors, err.defaultMessage)}">
                        <div>${err.defaultMessage}</div>
                    </c:if>
                    <c:set var="previousErrors" value="${previousErrors}${err.defaultMessage}" scope="page"/>
                </c:forEach>
            </div>
        </c:if>

        <form action="savePatient" method="post">

            <!-- First & Last Name -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" id="firstName" name="firstName"  oninput="validateFirstName()" onchange="CheckFirstName()" class="form-control" required>
                    <div id="firstnameError" class="form-text text-danger"></div>
                </div>
                <div class="col-md-6">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" id="lastName" name="lastName" onchange="CheckLastName()" oninput="validateLastName()" class="form-control" required>
                    <div id="lastnameError" class="form-text text-danger"></div>
                </div>
            </div>

            <!-- Phone & Email -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="number" class="form-control shadow-sm" id="phone" name="phone"
                           oninput="validatePhone()" onchange="CheckPhoneNumber()" value="${dto.phone}" placeholder="Enter phone number" required>
                    <div id="phoneError" class="form-text text-danger"></div>
                </div>
                <div class="col-md-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email"  oninput="validateEmail()" onchange="checkDoctorEmail()" class="form-control" required>
                    <div id="emailError" class="form-text text-danger"></div>
                </div>
            </div>

            <!-- Blood Type & Health Concern -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="bloodType" class="form-label">Blood Type</label>
                    <select id="bloodType" name="bloodType" class="form-select" required>
                        <option value="">Choose...</option>
                        <c:forEach var="type" items="${bloodTypes}">
                            <option value="${type}">${type.displayName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="age" class="form-label">Age</label>
                    <input type="number" class="form-control" id="age" name="age" required>
                    <div id="ageError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="healthConcern" class="form-label">Symptoms / Health Concern</label>
                    <textarea id="healthConcern" name="healthConcern" class="form-control" rows="1" required></textarea>
                </div>
            </div>

            <div class="col-md-6">
                <label for="appointmentDate" class="form-label">Date of Appointment</label>
                <input type="date" class="form-control" id="appointmentDate" name="appointmentDate" required>
                <div id="appointmentDateError" class="form-text text-danger"></div>
            </div>
            <div class="col-md-6">
                <label for="specialization" class="form-label fw-semibold">Specialization</label>
                <select class="form-select shadow-sm" id="specialization" onchange="fetchDoctor()" name="specializationName" required>
                    <option value="">Choose...</option>
                    <c:forEach var="spec" items="${specializations}">
                        <option value="${spec}">${spec}</option>
                    </c:forEach>
                </select>
                <div id="specializationError" class="form-text text-danger"></div>
            </div>

            <!-- Doctor -->
            <div class="mb-3">
                <label for="doctor" class="form-label">Doctor Name</label>
                <select id="doctor" name="doctor" class="form-select" onchange="fetchTimeSlot()"  required>
                    <option selected disabled>Select Doctor</option> <!-- This will be replaced by JS -->
                </select>
                <div id="doctorError" class="form-text text-danger"></div>
            </div>

            <!-- Slot -->
            <div class="mb-3">
                <label for="slot" class="form-label">Available Time Slot</label>
                <select id="slot" name="slot" class="form-select" required>
                    <option selected disabled>Select Doctor First</option>
                </select>
                <div id="slotError" class="form-text text-danger"></div>
            </div>
            <!-- Registration Fees -->
            <div class="mb-3">
                <label for="fees" class="form-label">Registration Fees</label>
                <input type="number" id="fees" name="fees" class="form-control" placeholder="Enter amount" required>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary px-4">Register</button>
            </div>

            <!-- Feedback Messages -->
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
<script src="Patient.js"></script>
</body>
</html>
