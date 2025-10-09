package com.xworkz.module.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DoctorDTO {

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;


    @NotNull(message = "PhoneNumber should not be null")
    @Min(value = 1000000000L, message = "Phone number should be 10 digits")
    @Max(value = 99999999999L, message = "Phone number should be 10 digits")
    private long phone;

    @NotNull(message = "Specialization is required")
    private String specializationName;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience must be a positive number")
    private Integer experience;

    @NotNull(message = "Address is required")
    @Size(min = 3, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @NotNull(message = "Gender is required")
    private String gender;

    @NotNull(message = "Degree is required")
    private String degree;

    // This field is for handling the file upload from the form
    private String images;

    private String timeSlot;


}