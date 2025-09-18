package com.xworkz.module.dto;

import com.xworkz.module.constant.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {


    @NotNull(message = "Name should not be empty")
    @Size(min=3,max=20,message = "name should contain between 3-20 character")
    @Pattern(regexp = "^[A-Z][A-Za-z\\s-]*$" ,message = "Name should start from capital and no digits or special character allowed")
    private String firstName;

    @NotNull(message = "Name should not be empty")
    @Size(min=1,max=20,message = "name should contain between 3-20 character")
    @Pattern(regexp = "^[A-Z][A-Za-z\\s-]*$" ,message = "Name should start from capital and no digits or special character allowed")
    private String lastName;

    @NotNull(message = "Email should not be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Email should send with @gmail.com")
    private String email;

    @NotNull(message = "PhoneNumber should not be null")
    @Min(value = 1000000000L, message = "Phone number should be 10 digits")
    @Max(value = 99999999999L, message = "Phone number should be 10 digits")
    private long phone;

    @NotNull(message = "Specialization should not be null")
    private Specialization specialization;


    @NotNull(message = "Experience should not be null")
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 50, message = "Experience cannot exceed 50 years")
    private int experience;

    @NotNull(message = "Address should not be empty")
    @Size(min=3, max=1000, message = "Address should be 3to1000 character")
    private String address;

    private String image;

    @NotNull(message = "Please select the gender")
    private String gender;

    @NotNull(message = "Please select the degree")
    private List<String> degree;

    private String timeSlot;



}
