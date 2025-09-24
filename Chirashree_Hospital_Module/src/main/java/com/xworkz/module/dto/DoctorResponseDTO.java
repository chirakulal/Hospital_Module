package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

       private int id;

        private String firstName;


        private String lastName;


        private String email;



        private long phone;


        private String specializationName;


        private Integer experience;

        private String address;

        private String gender;

        private String[] degree;

        private String images;

        private String timeSlot;

        private String createdBy;
        private Timestamp createdAt;
        private String updatedBy;
        private Timestamp updatedAt;
    }

