# Hospital_Module

# Hospital Management System
This is a  application for managing a hospital's administrative tasks, focusing on doctor and time slot management. The application provides an admin-facing dashboard to register new doctors, define their specializations and personal details, create available time slots, and assign these slots to specific doctors. It features a secure login system for the administrator using an OTP (One-Time Password) sent to a registered email.

# Features
Secure Admin Login: Admins can log in using their email, which triggers a one-time password (OTP) sent to that email address. The OTP is valid for 2 minutes.

Doctor Management: Admins can register new doctors by providing their details, including name, contact information, specialization, experience, and an image.

Time Slot Management: The application allows the creation of specific time slots (e.g., 9:00 AM - 10:00 AM) which can then be assigned to doctors.

Assigning Slots to Doctors: Admins can select a specialization and then choose from a list of available doctors and time slots to create a schedule.

Image Upload: Doctor profile pictures are handled and saved on the local file system.

# Technologies Used
Backend:

Java: The core programming language.

Spring Boot: The framework for building the application.

Spring MVC: For handling web requests and responses.

Spring Data JPA: For data persistence and database interactions.

JPA: The ORM (Object-Relational Mapping) tool.

Lombok: To reduce boilerplate code.

Frontend:

JSP (JavaServer Pages): For dynamic web pages.

JSTL (JavaServer Pages Standard Tag Library): For conditional logic in JSP.

Bootstrap 5: For responsive design and styling.

Database: The code uses JPA, suggesting it's configured with a database like MySQL or PostgreSQL, though the specific database is not mentioned in the provided code snippet.

Email Service: The application uses JavaMail API for sending OTP emails.

# Setup and Installation
Prerequisites
Java 8 or higher: Ensure a Java Development Kit (JDK) is installed.

Apache Maven: To manage project dependencies and build the application.

Database: A running instance of a relational database (e.g., MySQL).

Email Account: An email account with app-specific password enabled for sending OTPs. The current code uses a Gmail account (chirashreelk@gmail.com).

Configuration
Database: Configure your database connection in application.properties or application.yml.

# Properties

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Email Service: Update the email sender credentials in HospitalServiceImpl.java.

# Java

final String username = "your_email@gmail.com";
final String password = "your_app_password"; // Use an app password, not your regular password
Image Upload Path: Update the image upload path in HospitalController.java.

# Java

Path uploadPath = Paths.get("D:\\chiraimage\\HospitalProject\\DoctorProfile" + fileName);
// Change "D:\\chiraimage\\HospitalProject\\DoctorProfile" to your desired directory
Run the Application: You can run the application directly from your IDE or by using Maven.

# Bash

mvn spring-boot:run
Usage
Navigate to http://localhost:8080/Chirashree_Hospital_Module/admin.

Enter the configured admin email to receive an OTP.

Enter the OTP to access the dashboard.

From the dashboard, you can:

Add a new doctor: Click on the "Doctor" link to fill out the doctor registration form.

Add new time slots: Go to "Slot" to create new time slots.

Assign a slot: Use "Add Slot" to assign a time slot to a specific doctor and specialization.

Logout: Invalidate the session and return to the login page.

# Project Structure
com.xworkz.module.controller: Contains the HospitalController for handling all web requests.

com.xworkz.module.service: Includes the HospitalServiceImpl class with the business logic.

com.xworkz.module.repository: Contains the HospitalRepoImpl class for all database operations.

com.xworkz.module.dto: Data Transfer Objects for passing data between layers.

com.xworkz.module.entity: JPA entities mapping to database tables.

com.xworkz.module.constant: Enumerations for specializations.

src/main/webapp/WEB-INF/views: JSP files for the web pages.

src/main/resources: Configuration files like application.properties.
