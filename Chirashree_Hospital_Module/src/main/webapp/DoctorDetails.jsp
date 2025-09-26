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
</header>

<!-- Section -->
<section class="bg-image d-flex justify-content-center align-items-start"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh; padding-top: 40px;">
    <div class="card shadow-lg p-4 w-100"
         style="max-width: 800px; background: rgba(255,255,255,0.95); border-radius: 12px;">

        <h3 class="text-success mb-4 text-center fw-bold">Doctor Personal Information</h3>

        <!-- Error Messages -->
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

        <!-- Form -->
        <form action="saveDoctor" method="post"  enctype="multipart/form-data" class="row g-3">

            <!-- First Name -->
            <div class="col-md-6">
                <label for="firstName" class="form-label fw-semibold">First Name</label>
                <input type="text" class="form-control shadow-sm" id="firstName" name="firstName"
                       placeholder="Enter first name" oninput="validateFirstName()" onchange="CheckFirstName()" value="${dto.firstName}" required>
                <div id="firstnameError" class="form-text text-danger"></div>
            </div>

            <!-- Last Name -->
            <div class="col-md-6">
                <label for="lastName" class="form-label fw-semibold">Last Name</label>
                <input type="text" class="form-control shadow-sm" id="lastName" name="lastName"
                       placeholder="Enter last name" onchange="CheckLastName()" oninput="validateLastName()"  value="${dto.lastName}" required>
                <div id="lastnameError" class="form-text text-danger"></div>
            </div>

            <!-- Email -->
            <div class="col-md-6">
                <label for="email" class="form-label fw-semibold">Email</label>
                <input type="email" class="form-control shadow-sm" id="email" name="email"
                       oninput="validateEmail()" onchange="checkDoctorEmail()" value="${dto.email}" placeholder="doctor@example.com" required>
                <div id="emailError" class="form-text text-danger"></div>
            </div>

            <!-- Phone -->
            <div class="col-md-6">
                <label for="phone" class="form-label fw-semibold">Phone Number</label>
                <input type="number" class="form-control shadow-sm" id="phone" name="phone"
                       oninput="validatePhone()" onchange="CheckPhoneNumber()" value="${dto.phone}" placeholder="Enter phone number" required>
                <div id="phoneError" class="form-text text-danger"></div>
            </div>

            <!-- Specialization -->

                <div class="col-md-6">
                    <label for="specialization" class="form-label fw-semibold">Specialization</label>
                    <select class="form-select shadow-sm" id="specialization" name="specializationName" required>
                        <option value="">Choose...</option>
                        <c:forEach var="spec" items="${specializations}">
                            <option value="${spec}">${spec}</option>
                        </c:forEach>
                    </select>
                </div>

            <!-- Experience -->
            <div class="col-md-6">
                <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
                <input type="number" class="form-control shadow-sm" id="experience" name="experience"
                       oninput="validateExperience()" min="0" max="50" value="${dto.experience}" required>
                <div id="experienceError" class="form-text text-danger"></div>
            </div>

            <!-- Address -->
            <div class="col-12">
                <label for="address" class="form-label fw-semibold">Address</label>
                <textarea class="form-control shadow-sm" id="address" name="address" rows="2"
                          oninput="validateAddress()" placeholder="Enter full address" value="${dto.address}" required></textarea>
                <div id="addressError" class="form-text text-danger"></div>
            </div>

            <!-- Profile Picture -->
            <div class="col-md-6">
                <label for="images" class="form-label fw-semibold">Upload Profile Picture</label>
                <input type="file" class="form-control shadow-sm" id="images" name="image" accept="image/*" required>
                <small id="profilePictureError" class="text-danger"></small>
            </div>

            <!-- Gender -->
            <div class="col-md-6">
                <label class="form-label fw-semibold">Gender</label>
                <div class="d-flex align-items-center">
                    <div class="form-check me-3">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="Male"
                               onclick="validateGender()" required>
                        <label class="form-check-label" for="male">Male</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="Female"
                               onclick="validateGender()">
                        <label class="form-check-label" for="female">Female</label>
                    </div>
                </div>
                <div id="genderError" class="form-text text-danger"></div>
            </div>

            <!-- Degree (Text Input) -->
            <div class="col-md-6">
                <label for="degree" class="form-label fw-semibold">Degree</label>
                <input type="text" id="degree" name="degree" class="form-control shadow-sm"
                       placeholder="Enter Degree (e.g. MBBS, MD, PhD)" required>
                <div id="degreeError" class="form-text text-danger"></div>
            </div>

            <!-- Submit Button -->
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-success px-5 mt-3 shadow-sm">Save Details</button>
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
</section>

<!-- Footer -->
<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<!-- Degree Dropdown Script -->
<script>
    const checkboxes = document.querySelectorAll(".degree-option");
    const dropdownBtn = document.getElementById("degreeDropdown");

    checkboxes.forEach(cb => {
        cb.addEventListener("change", () => {
            let selected = [];
            checkboxes.forEach(c => {
                if (c.checked) selected.push(c.value);
            });
            dropdownBtn.textContent = selected.length > 0 ? selected.join(", ") : "Select Degree";
        });
    });
     function resetFormAfterSuccess() {
        // Check if the success message is present
        const successAlert = document.getElementById('successAlert');
        if (successAlert) {
            const form = document.querySelector('form');
            if (form) {
                form.reset();
                // Reset Specialization dropdown
                document.getElementById('specialization').selectedIndex = 0;
                // Reset Degree dropdown text
                document.getElementById('degreeDropdown').textContent = "Select Degree";
                // Uncheck all Degree checkboxes
                document.querySelectorAll('.degree-option').forEach(cb => {
                    cb.checked = false;
                });
            }
        }
    }

    // Call the function on page load
    window.onload = resetFormAfterSuccess;
</script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>
</body>
</html>
