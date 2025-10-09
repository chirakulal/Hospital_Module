package com.xworkz.module.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "doctor")
@NoArgsConstructor
@Entity
@Table(name = "doctor_time_slots")

@NamedQuery(name = "TimeSlotEntity.getTimeSlotIdByTime",
        query = "Select t from TimeSlotEntity t where timeSlot=:timeSlot"
)
public class TimeSlotEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeslot_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)   // Many slots can belong to one doctor
    @JoinColumn(name = "doctor_id", referencedColumnName = "id_doctor", nullable = false)
    private DoctorEntity doctor;


    @Column(name = "time_slot", nullable = false)
    private String timeSlot;


}
