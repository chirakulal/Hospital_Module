<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Healing Hands Hospital - Doctors List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            flex-direction: column;
        }
        .main-content {
            flex: 1;
        }



    </style>
</head>
<body>

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

<div class="main-content d-flex justify-content-center align-items-center p-4"
     style="background-image: url('/Chirashree_Hospital_Module/resources/images/hospital.png');
            background-size: cover; background-position: center;">
    <div class="card shadow-lg p-4 w-100" style="max-width: 1200px; background: rgba(255, 255, 255, 0.95); border-radius: 12px;">

        <div class="container-fluid mt-5">
            <h2 class="mb-4 text-center">Doctors List</h2>

            <div class="table-responsive">
                <table class="table table-success table-bordered table-striped table-hover align-middle">
                    <thead class="bg-success-rgb ">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Specialization</th>
                        <th scope="col">Experience</th>
                        <th scope="col">Address</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Degrees</th>
                        <th scope="col">Profile Image</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="doctor" items="${doctorDTOS}">
                        <tr>
                            <td>${doctor.id}</td>
                            <td>${doctor.firstName}</td>
                            <td>${doctor.lastName}</td>
                            <td>${doctor.email}</td>
                            <td>${doctor.phone}</td>
                            <td>${doctor.specializationName}</td>
                            <td>${doctor.experience}</td>
                            <td>${doctor.address}</td>
                            <td>${doctor.gender}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty doctor.degree}">
                                        ${fn:join(doctor.degree, ', ')}
                                    </c:when>
                                    <c:otherwise>
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${not empty doctor.images}">
                                        <img src="download?fileName=${doctor.images}" alt="Profile" class="img-fluid rounded-circle" style="width: 60px; height: 60px; object-fit: cover;"/>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">No Image</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="updateDoctor?email=${doctor.email}" class="btn btn-success btn-sm me-2">Update</a>
                                <a href="deleteDoctor?id=${doctor.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this doctor?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</div>

<footer class="bg-success text-white text-center py-3 mt-auto">
    <div class="container">
        <p class="mb-1">&copy; 2025 Healing Hands Hospital. All rights reserved.</p>
        <small>Designed with Bootstrap</small>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>