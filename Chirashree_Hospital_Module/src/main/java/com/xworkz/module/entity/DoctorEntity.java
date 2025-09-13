package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "doctor_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoctor_details")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private long phone;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience")
    private int experience;

    @Column(name = "address")
    private String address;

    @Column(name = "imagepath")
    private String image;

    @Column(name = "gender")
    private String gender;

    @Column(name = "timestart")
    private Time timingStart;

    @Column(name = "timeend")
    private Time timingEnd;

}
