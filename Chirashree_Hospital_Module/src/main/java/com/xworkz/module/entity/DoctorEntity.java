package com.xworkz.module.entity;

import com.xworkz.module.constant.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "DoctorEntity.getNamesBySpecialization",query ="Select concat(d.firstName,' ',d.lastName) from DoctorEntity d where d.specialization =:specialization and d.timeSlot is null")
@NamedQuery(name = "countLastname",query = "Select count(e.lastName) from DoctorEntity e where e.lastName =:lastName")
@NamedQuery(name = "countPhoneNumber",query = "Select count(e.phone) from  DoctorEntity e where e.phone =:phone")
@NamedQuery(name = "DoctorEntity.getByEmail", query = "select count(e.email)  from DoctorEntity e where e.email=:email")
@NamedQuery(name = "DoctorEntity.updateSlotByName", query = "update DoctorEntity d SET d.timeSlot = :timeSlot where concat(d.firstName, ' ', d.lastName) = :doctorName"
)
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

    @Enumerated(EnumType.STRING) // Store as String in DB
    @Column(name = "specialization")
    private Specialization specialization;

    @Column(name = "experience")
    private int experience;

    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    @Column(name = "gender")
    private String gender;

    @ElementCollection
    @CollectionTable(name = "doctor_degrees", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "degree")
    private List<String> degree = new ArrayList<>();

    @Column(name = "timeslot")
    private String timeSlot;




}
