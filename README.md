# Healing Hands Hospital Management System

## Project Overview

This is a application designed to streamline administrative tasks for a hospital, with a primary focus on doctor and time slot management. The application features a secure, admin-facing dashboard where authorized users can register new doctors, create available time slots, and efficiently assign these slots to doctors based on their specialization. A key security feature is the **One-Time Password (OTP)** based login system, ensuring that only authenticated administrators can access the system.

## ✨ Features

* **Secure Admin Login**: Log in to the system using a secure, email-based OTP. Each OTP is valid for a limited time (2 minutes).
* **Doctor Management**: A comprehensive form allows admins to add new doctors, capturing their personal details, professional experience, specialization, and a profile picture.
* **Time Slot Management**: Create and manage specific appointment time slots (e.g., `9:00 AM - 10:00 AM`) that doctors can be assigned to.
* **Doctor Scheduling**: Assign a time slot to a doctor from a list filtered by their specialization, simplifying the process of creating a doctor's schedule.
* **Image Uploads**: Handles the upload and storage of doctor profile images on the local file system.

## 🛠️ Technologies Used

### Backend

* **Java**: The core programming language.
* **Spring MVC**: Handles all web requests and is responsible for the controller logic.
* **Spring Data JPA**: Simplifies data access layer and repository implementations.
* **Hibernate**: The Object-Relational Mapping (ORM) framework for interacting with the database.
* **Lombok**: Reduces boilerplate code (e.g., getters, setters, constructors).
* **JavaMail API**: Used for sending OTP emails to the admin.

### Frontend

* **JSP (JavaServer Pages)**: The templating language for generating dynamic HTML content.
* **JSTL (JavaServer Pages Standard Tag Library)**: Used for conditional logic and iteration in JSP pages.
* **Bootstrap 5**: Provides a responsive and modern design for the user interface.

### Database

* **JPA-compliant database**: The application is configured to work with a relational database (e.g., **MySQL**, **PostgreSQL**).

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

* **Java Development Kit (JDK) 8+**
* **Apache Maven**
* A running instance of your preferred **relational database** (e.g., MySQL).
* An **email account** with an app-specific password enabled for sending emails (the current code uses a Gmail account).

### Installation and Configuration

1.  **Clone the repository**:
    ```bash
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    cd your-repository-name
    ```
2.  **Configure Database**: Update the database credentials in `src/main/resources/application.properties`.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  **Set up Email Service**:
    In `src/main/java/com/xworkz/module/service/HospitalServiceImpl.java`, update the email sender credentials. **Note**: For security, it is highly recommended to use an **app password** instead of your regular email password.
    ```java
    final String username = "your_email@gmail.com";
    final String password = "your_app_password";
    ```
4.  **Configure Image Upload Path**:
    In `src/main/java/com/xworkz/module/controller/HospitalController.java`, change the hardcoded image upload path to a directory on your machine.
    ```java
    Path uploadPath = Paths.get("D:\\chiraimage\\HospitalProject\\DoctorProfile" + fileName);
    // Change this path to your desired directory
    ```
5.  **Build and Run**: Use Maven to start the application.
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## 💻 Usage

1.  Open your web browser and go to `http://localhost:8080/Chirashree_Hospital_Module/admin`.
2.  Enter the configured admin email to receive a login OTP.
3.  Enter the OTP to access the main dashboard.
4.  From the dashboard, you can navigate to different pages:
    * `doctor`: Register a new doctor.
    * `slot`: Create a new time slot.
    * `addslot`: Assign an existing time slot to a doctor.
    * `logout`: Log out of the admin session.

## 📁 Project Structure

* `src/main/java/com/xworkz/module/controller`: Contains the `HospitalController`, which manages all web-related requests.
* `src/main/java/com/xworkz/module/service`: Holds the business logic, handled by `HospitalServiceImpl`.
* `src/main/java/com/xworkz/module/repository`: Manages data access and database operations in `HospitalRepoImpl`.
* `src/main/java/com/xworkz/module/dto`: Data Transfer Objects (DTOs) for data exchange between layers.
* `src/main/java/com/xworkz/module/entity`: JPA entities representing database tables.
* `src/main/java/com/xworkz/module/constant`: Contains `enum` types like `Specialization`.
* `src/main/webapp/WEB-INF/views`: Stores the JSP files for the user interface.
* `src/main/resources`: Holds configuration files like `application.properties`.
