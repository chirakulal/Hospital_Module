package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hospital_details")
@NamedQuery(name = "updateotp",query = "update HospitalEntity e set e.otp=:otp , e.time=:time where e.email =:email ")
@NamedQuery(name = "countEmail",query = "select count(e.email) from HospitalEntity e where email=:email")
public class HospitalEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hospital_email")
    private String email;

    @Column(name = "otp_login")
    private int otp;

    @Column(name = "time")
    private LocalDateTime time;
    private String password;
}
