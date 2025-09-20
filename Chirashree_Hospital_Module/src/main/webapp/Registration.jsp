<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.xworkz.module.constant" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Patient Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4 rounded-4">
        <h3 class="mb-4 text-center">Patient Registration Form</h3>

        <form action="registerPatient" method="post">

            <!-- First & Last Name -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" id="firstName" name="firstName" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" required>
                </div>
            </div>

            <!-- Phone & Email -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="tel" id="phone" name="phone" class="form-control"
                           pattern="[6-9][0-9]{9}" placeholder="10-digit number" required>
                </div>
                <div class="col-md-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email" class="form-control" required>
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
                    <label for="healthConcern" class="form-label">Symptoms / Health Concern</label>
                    <textarea id="healthConcern" name="healthConcern" class="form-control" rows="1" required></textarea>
                </div>
            </div>

            <!-- Specialization -->
            <div class="mb-3">
                <label for="specialization" class="form-label">Specialization</label>
                <select id="specialization" name="specialization" class="form-select" required>
                    <option value="">Choose...</option>
                    <c:forEach var="spec" items="${specializations}">
                        <option value="${spec}">${spec.displayName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Doctor -->
            <div class="mb-3">
                <label for="doctor" class="form-label">Doctor Name</label>
                <select id="doctor" name="doctor" class="form-select" required>
                    <option value="">Choose...</option>
                    <c:forEach var="doc" items="${doctors}">
                        <option value="${doc.id}">${doc.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Slot -->
            <div class="mb-3">
                <label for="slot" class="form-label">Appointment Slot</label>
                <input type="time" id="slot" name="slot" class="form-control" required>
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
        </form>
    </div>
</div>

</body>
</html>
