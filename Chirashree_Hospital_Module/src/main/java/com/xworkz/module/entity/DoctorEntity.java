package com.xworkz.module.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "doctor_details")
@NamedQuery(name = "countPhoneNumber",
        query = "Select count(e.phone) from  DoctorEntity e where e.phone =:phone")
@NamedQuery(name = "DoctorEntity.getByEmail",
        query = "select count(e.email)  from DoctorEntity e where e.email=:email")
@NamedQuery(name = "DoctorEntity.getNamesBySpecialization",query ="Select concat(d.firstName,' ',d.lastName) from DoctorEntity d where d.specializationName =:specializationName and d.timeSlot is null")
@NamedQuery(name = "DoctorEntity.updateSlotByName", query = "update DoctorEntity d SET d.timeSlot = :timeSlot where concat(d.firstName, ' ', d.lastName) = :doctorName")

@NamedQuery(
        name = "DoctorEntity.getAllDoctorDetails",
        query = "SELECT DISTINCT d FROM DoctorEntity d LEFT JOIN FETCH d.profilePicture LEFT JOIN FETCH d.degree"
)

@NamedQuery(
        name = "DoctorEntity.getAllDoctorDetailsByEmail",
        query = "SELECT  d FROM DoctorEntity d LEFT JOIN FETCH d.profilePicture LEFT JOIN FETCH d.degree where d.email=:email"
)


public class DoctorEntity {

    @Id
    @Column(name = "id_doctor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Long phone;

    @Column(name = "specialization", nullable = false)
    private String specializationName;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "address", nullable = false)
    private String address;

    // Stores the file path or a unique identifier to the stored image
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idimage", referencedColumnName = "idimage")
    private ImageEntity profilePicture;


    @Column(name = "gender", nullable = false)
    private String gender;

    // Storing multiple degrees as a comma-separated string
    @ElementCollection
    @CollectionTable(name = "doctor_degrees", joinColumns = @JoinColumn(name = "id_doctor"))
    @Column(name = "degree")
    private List<String> degree = new ArrayList<>();

    @Column(name = "timeslot")
    private String timeSlot;


    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}