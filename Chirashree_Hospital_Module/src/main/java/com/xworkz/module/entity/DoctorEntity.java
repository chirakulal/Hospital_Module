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
@NamedQuery(
        name = "DoctorEntity.getNamesBySpecialization",
        query = "SELECT distinct concat(d.firstName,' ',d.lastName) " +
                "FROM DoctorEntity d " +
                "LEFT JOIN d.timeSlots t " +
                "WHERE d.specializationName = :specializationName "
)

@NamedQuery(
        name = "DoctorEntity.getAllDoctorDetailsWithProjection",
        query = "SELECT d, pp.savedName FROM DoctorEntity d  JOIN d.profilePicture pp "
)

@NamedQuery(
        name = "DoctorEntity.getAllDoctorDetailsByEmail",
        query = "SELECT  d FROM DoctorEntity d LEFT JOIN FETCH d.profilePicture where d.email=:email"
)

@NamedQuery(
        name = "DoctorEntity.getAllDoctorDetailsById",
        query = "SELECT  d FROM DoctorEntity d LEFT JOIN FETCH d.profilePicture where d.id=:id"
)
@NamedQuery(
        name = "DoctorEntity.getTimeSlotByEmail",
        query = "SELECT t.timeSlot " +
                "FROM DoctorEntity d " +
                "JOIN d.timeSlots t " +
                "WHERE d.email = :email"
)

@NamedQuery(
        name = "DoctorEntity.findByFullName",
        query = "SELECT d FROM DoctorEntity d WHERE CONCAT(d.firstName, ' ', d.lastName) = :doctorName"
)
@NamedQuery(
        name = "DoctorEntity.DeleteByEmail",
        query = "DELETE FROM DoctorEntity d WHERE d.email = :email"
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
    @Column(name = "degree", nullable = false)
    private String degree;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlotEntity> timeSlots = new ArrayList<>();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}