package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "time_slots")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "TimeEntity.getTime",query = "Select concat(t.startTime,'--',t.endTime) from TimeEntity t")
public class TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtime")
    private int id;

    @Column(name = "time_start")
    private LocalTime  startTime;

    @Column(name = "time_end")
    private LocalTime endTime;
}
