<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en" >
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

        <div class="container">
            <h1 class="mb-4">Submit a Time Slot Range</h1>

            <form action="schedule" method="get">
                <div class="mb-3">
                    <label for="specialization" class="form-label">Select Specialization:</label>
                    <select class="form-select" id="specialization" name="specialization" required>
                        <option value="">Select..</option>
                        <c:forEach var="spec" items="${specializations}">
                            <option value="${spec}">${spec.displayName}</option>
                        </c:forEach>
                    </select>

                </div>

                <button type="submit" class="btn btn-success">Schedule</button>
            </form>
            <hr/>

            <!-- Second Form (Doctor + Time Slot) -->
            <c:if test="${scheduleClicked}">
                <form action="saveSlot" method="post">
                    <div class="mb-3">
                        <label for="doctorName" class="form-label">Doctor Name:</label>
                        <select class="form-select" id="doctorName" name="doctorName" required>
                            <option value="">Select Doctor</option>
                            <c:forEach var="doc" items="${doctorNames}">
                                <option value="${doc}">${doc}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="timeSlot" class="form-label">Select Time Slot:</label>
                        <select class="form-select" id="timeSlot" name="timeSlot" required>
                            <option value="">Select Time</option>
                            <c:forEach var="time" items="${timeList}">
                                <option value="${time}">${time}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Save Slot</button>
                </form>
            </c:if>
        </div>
    </div>
</section>

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