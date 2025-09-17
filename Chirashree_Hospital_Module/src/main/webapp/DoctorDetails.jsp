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
            <a class="navbar-brand d-flex align-s-center" href="#">
                <img src="/Chirashree_Hospital_Module/resources/images/logo.png"
                     alt="Logo" width="70" height="64" class="d-inline-block align-text-top me-2">
                <span class="fw-bold fs-4 text-success font-Courier New">Healing Hands Hospital</span>
            </a>
        </div>
    </nav>
</header>

<section class="bg-image d-flex justify-content-center align-items-start"
         style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
                background-size: cover;
                background-position: center;
                min-height: 100vh; padding-top: 40px;">
    <div class="card shadow-lg p-4" style="width: 90%; max-width: 700px; background: rgba(255,255,255,0.95);">
        <h3 class="text-success mb-3 text-center">Doctor Personal Information</h3>
       <span style="color:red">${errors}</span>
        <form action="saveDoctor" method="post" enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-6">
                    <label for="firstName" class="form-label fw-semibold">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" oninput="validateFirstName()" onchange=" CheckFirstName()">
                    <div id="firstnameError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="lastName" class="form-label fw-semibold">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" onchange="CheckLastName()" oninput="validateLastName()" required>
                    <div id="lastnameError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="email" class="form-label fw-semibold">Email</label>
                    <input type="email" class="form-control" id="email" name="email" oninput="validateEmail()" onchange="CheckEmail()" placeholder="doctor@example.com" required>
                    <div id="emailError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="phone" class="form-label fw-semibold">Phone Number</label>
                    <input type="tel" class="form-control" id="phone" name="phone" oninput="validatePhone()" onchange="CheckPhoneNumber()" placeholder="Enter phone number" required>
                    <div id="phoneError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <select class="form-select" id="specialization" name="specialization" required>
                        <option value="">Choose...</option>
                        <c:forEach var="spec" items="${specializations}">
                            <option value="${spec}">${spec.displayName}</option>
                        </c:forEach>
                    </select>

                    <div id="specializationError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
                    <input type="number" class="form-control" id="experience" name="experience" oninput="validateExperience()" min="0" max="50" required>
                    <div id="experienceError" class="form-text text-danger"></div>
                </div>

                <div class="col-12">
                    <label for="address" class="form-label fw-semibold">Address</label>
                    <textarea class="form-control" id="address" name="address" rows="2" oninput="validateAddress()" placeholder="Enter full address" required></textarea>
                    <div id="addressError" class="form-text text-danger"></div>
                </div>

                <div class="col-md-6">
                    <label for="images" class="form-label">Upload Profile Picture</label>
                    <input type="file" class="form-control" id="images" name="images" accept="images/*" required>
                    <small id="profilePictureError" class="text-danger"></small>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-semibold">Gender</label><br>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="Male" onclick="validateGender()" required>
                        <label class="form-check-label" for="male">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="female" onclick="validateGender()" value="Female">
                        <label class="form-check-label" for="female">Female</label>
                    </div>
                    <div id="genderError" class="form-text text-danger"></div>
                </div>
                <div class="col-md-6">
                    <label for="degree" class="form-label fw-semibold">Degree</label>

                    <div class="dropdown w-100">
                        <button class="btn btn-outline-secondary dropdown-toggle w-100 text-start" type="button" id="degreeDropdown" id="degree" data-bs-toggle="dropdown" aria-expanded="false">
                            Select Degree
                        </button>

                        <ul class="dropdown-menu w-100 p-2" aria-labelledby="degreeDropdown" style="max-height: 300px; overflow-y: auto;">

                            <li><strong class="dropdown-header">Undergraduate</strong></li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="MBBS" id="degree-mbbs" name="degree">
                                    <label class="form-check-label" for="degree-mbbs">MBBS</label>
                                </div>
                            </li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="BDS" id="degree-bds" name="degree">
                                    <label class="form-check-label" for="degree-bds">BDS</label>
                                </div>
                            </li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="BAMS" id="degree-bams" name="degree">
                                    <label class="form-check-label" for="degree-bams">BAMS</label>
                                </div>
                            </li>

                            <li><hr class="dropdown-divider"></li>
                            <li><strong class="dropdown-header">Postgraduate</strong></li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="MD" id="degree-md" name="degree">
                                    <label class="form-check-label" for="degree-md">MD</label>
                                </div>
                            </li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="MS" id="degree-ms" name="degree">
                                    <label class="form-check-label" for="degree-ms">MS</label>
                                </div>
                            </li>

                            <li><hr class="dropdown-divider"></li>
                            <li><strong class="dropdown-header">Other</strong></li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input degree-option" type="checkbox" value="PhD" id="degree-phd" name="degree">
                                    <label class="form-check-label" for="degree-phd">PhD</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                    <div class="col-12 text-center">
                    <button type="submit" class="btn btn-success px-5 mt-3">Save Details</button>
                </div>
            </div>
        </form>
    </div>
</section>

<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed using Bootstrap</small>
    </div>
</footer>
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
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<script src="validation.js"></script>
</body>
</html>