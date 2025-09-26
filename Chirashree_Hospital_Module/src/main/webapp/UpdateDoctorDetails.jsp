<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
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

<header>
    <nav class="navbar shadow-sm" style="background-color: var(--bs-success-bg-subtle);">
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png" alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>
            <div class="d-flex ms-auto">
            <a href="updateDetails" class="btn btn-outline-success fw-semibold">Update Details</a>
            <a href="dashboard-success" class="btn btn-outline-success fw-semibold">Back to Dashboard</a>
            </div>
        </div>
    </nav>
</header>

<section class="bg-image d-flex justify-content-center align-items-start"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png'); background-size: cover; background-position: center; min-height: 100vh; padding-top: 40px;">
    <div class="card shadow-lg p-4 w-100" style="max-width: 800px; background: rgba(255,255,255,0.95); border-radius: 12px;">
        <div class="container mt-5">
            <div class="card p-4">
                <h2 class="mb-4 text-center">Update Doctor Details</h2>
                <form action="saveUpdate" method="post" enctype="multipart/form-data">


                    <div class="mb-3">
                        <label for="firstName" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" oninput="validateFirstName()" onchange="CheckFirstName()" value="${doctor.firstName}" required>
                    </div>

                    <div class="mb-3">
                        <label for="lastName" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" onchange="CheckLastName()" oninput="validateLastName()" value="${doctor.lastName}" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${doctor.email}" readonly>
                    </div>

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="phone" class="form-label fw-semibold">Phone Number</label>
                            <input type="number" class="form-control shadow-sm" id="phone" name="phone"  oninput="validatePhone()" onchange="CheckPhoneNumber()" value="${doctor.phone}" placeholder="Enter phone number" readonly>
                            <div id="phoneError" class="form-text text-danger"></div>
                        </div>

                        <div class="col-md-6">
                            <label for="specialization" class="form-label fw-semibold">Select Specialization:</label>
                            <select class="form-select shadow-sm" id="specialization" name="specializationName" required>
                                <option value="">Select...</option>
                                <c:forEach var="spec" items="${specializations}">
                                    <option value="${spec}" ${doctor.specializationName eq spec ? 'selected' : ''}>${spec}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
                            <input type="number" class="form-control shadow-sm" id="experience" name="experience"  oninput="validateExperience()" min="0" max="50" value="${doctor.experience}" required>
                            <div id="experienceError" class="form-text text-danger"></div>
                        </div>

                        <div class="col-md-6">
                            <label for="degree" class="form-label fw-semibold">Degree</label>
                            <input type="text" id="degree" name="degree" class="form-control shadow-sm"
                                   placeholder="Enter Degree (e.g. MBBS, MD, PhD)" value="${doctor.degree}" required>
                            <div id="degreeError" class="form-text text-danger"></div>
                        </div>

                        <div class="col-12">
                            <label for="address" class="form-label fw-semibold">Address</label>
                            <textarea class="form-control shadow-sm" id="address" name="address" oninput="validateAddress()" rows="2" placeholder="Enter full address" required>${doctor.address}</textarea>
                            <div id="addressError" class="form-text text-danger"></div>
                        </div>

                        <!-- Profile Image Preview -->
                        <div class="col-md-6 text-center">
                            <label class="form-label fw-semibold d-block mb-2">Profile Picture</label>

                            <!-- Image Preview -->
                            <div class="position-relative d-inline-block mb-2">
                                <img id="profilePreview"
                                     src="<c:choose><c:when test='${not empty doctor.images}'>download?fileName=${doctor.images}</c:when><c:otherwise>/Chirashree_Hospital_Module/resources/images/default-profile.png</c:otherwise></c:choose>"
                                     alt="Profile"
                                     class="rounded-circle shadow-sm"
                                     style="width: 120px; height: 120px; object-fit: cover; border: 3px solid #198754; transition: transform 0.3s;">
                                <span class="position-absolute bottom-0 end-0 bg-success text-white rounded-circle p-1" style="cursor: pointer; font-size: 0.9rem;">
            &#9998; <!-- pencil icon for edit -->
        </span>
                            </div>

                            <!-- File Input -->
                            <input type="file" class="form-control shadow-sm" id="images" name="image" accept="image/*">
                            <small class="form-text text-muted">Leave blank to keep the current image.</small>
                            <input type="hidden" name="oldImage" value="${doctor.images}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Gender</label>
                            <div class="d-flex align-items-center">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="gender" id="male" value="Male" ${doctor.gender eq 'Male' ? 'checked' : ''} required>
                                    <label class="form-check-label" for="male">Male</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="gender" id="female" value="Female" ${doctor.gender eq 'Female' ? 'checked' : ''}>
                                    <label class="form-check-label" for="female">Female</label>
                                </div>
                            </div>
                            <div id="genderError" class="form-text text-danger"></div>
                        </div>



                        <hr class="my-4">
                        <button type="submit" class="btn bg-success text-white">Save Changes</button>
                        <a href="updateDetails" class="btn btn-secondary">Cancel</a>
                    </div>
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

<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>

<!-- Scripts -->
<script>
    // Degree dropdown update
    document.addEventListener("DOMContentLoaded", function () {
        const checkboxes = document.querySelectorAll(".degree-option");
        const dropdownButton = document.getElementById("degreeDropdown");

        function updateDropdownText() {
            const selected = [];
            checkboxes.forEach(cb => { if (cb.checked) selected.push(cb.value); });
            dropdownButton.textContent = selected.length > 0 ? selected.join(", ") : "Select Degree";
        }

        updateDropdownText();
        checkboxes.forEach(cb => cb.addEventListener("change", updateDropdownText));

        // Profile image preview
        const fileInput = document.getElementById("images");
        const preview = document.getElementById("profilePreview");

        fileInput.addEventListener("change", function () {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.src = e.target.result;
                }
                reader.readAsDataURL(file);
            }
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>
</body>
</html>
