package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "slot_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "SlotEntity.getTimeBySpecialization",
        query = "SELECT CONCAT(s.startTime, '--', s.endTime) " +
                "FROM SlotEntity s " +
                "WHERE s.specializationName = :specializationName"
)
public class SlotEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slot")
    private long id;

    @Column(nullable = false, length = 50)
    private String specializationName;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;



}
