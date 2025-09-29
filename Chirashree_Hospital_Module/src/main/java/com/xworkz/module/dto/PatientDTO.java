package com.xworkz.module.dto;

import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.TimeSlotEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {
    // First Name: Not Null, Min/Max size
    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    private String firstName;

    // Last Name: Not Null, Min/Max size
    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    private String lastName;

    // Phone: Not Null, Pattern for digit count (e.g., 10 digits)
    @NotNull(message = "Phone number is required.")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits.")
    private Long phone;

    // Email: Not Null, Valid Email format
    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    // Blood Type: Not Null/Blank (assuming it's a string from a dropdown)
    @NotBlank(message = "Blood type must be selected.")
    private String bloodType;

    // Age: Not Null, Minimum value (e.g., 1)
    @NotNull(message = "Age is required.")
    @Min(value = 1, message = "Age must be a positive number.")
    @Max(value = 120, message = "Age is too high.")
    private Integer age;

    // Health Concern: Not Null/Blank
    @NotBlank(message = "Health concern/symptoms field cannot be empty.")
    private String healthConcern;

    // Appointment Date: Not Null, Must be in the future (or present)
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  // 👈 tells Spring how to parse
    private LocalDate appointmentDate;

    // Specialization: Not Null/Blank
    @NotBlank(message = "Specialization must be selected.")
    private String specializationName;

    // Doctor: Not Null (assuming this is an ID or linked object)
    @NotNull(message = "Doctor must be assigned/selected.")
    private String doctor; // Or Integer doctorId if submitting just the ID

    // Time Slot: Not Null (assuming this is an ID or linked object)
    @NotNull(message = "Time slot must be selected.")
    private String slot;

    // Fees: Not Null, Minimum value
    @NotNull(message = "Fees amount is required.")
    @Min(value = 0, message = "Fees cannot be negative.")
    private Double fees;


}
