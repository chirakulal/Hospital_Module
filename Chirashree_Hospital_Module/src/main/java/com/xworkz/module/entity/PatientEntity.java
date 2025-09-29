package com.xworkz.module.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.xworkz.module.dto.RegistrationIdGenerator.generateRandomNumeric;


@Getter
@Setter
@ToString(exclude = {"doctor", "slot"})
@Entity
@Table(name = "patient_details")
@NamedQuery(
        name = "PatientEntity.getByEmail",
        query = "select  e from PatientEntity e where e.email=:email")
public class PatientEntity {

    @Id
    @Column(name = "id_patient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Primary Key

    @Column(name = "reg_id", unique = true, nullable = false)
    private String registrationId;  // Unique ID (e.g., JOSHITH-240926-ABCD12)

    // Patient Personal Info
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", unique = true, nullable = false)
    private Long phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "age")
    private Integer age;

    @Column(name = "health_concern")
    private String healthConcern;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "specialization")
    private String specializationName;

    // --- FOREIGN KEY RELATIONSHIP ---
    // Many Patients to One Doctor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_fk", referencedColumnName = "id_doctor", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "timeslot_fk",
            referencedColumnName = "timeslot_id", // <-- CORRECTED NAME
            nullable = false
    )
    private TimeSlotEntity slot;

    // Fees for the appointment

    @Column(name = "fees")
    private Double fees;



    @PrePersist
    protected void onCreate() {
        if (this.registrationId == null) {
            this.registrationId = generateIdFromEntityData();
        }
    }

    private String generateIdFromEntityData() {
        // Ensure you have these imports for LocalDate and DateTimeFormatter
        // You also need to handle the full namePart logic (first word) to match your example.
        String namePart = (this.firstName != null && !this.firstName.isEmpty())
                ? this.firstName.trim().split("\\s+")[0].toUpperCase()
                : "P"; // Using 'P' as default if null/empty.

        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        // This now works because of the static import above:
        String randomPart = generateRandomNumeric(6);

        return namePart + "-" + datePart + "-" + randomPart;
    }
}
