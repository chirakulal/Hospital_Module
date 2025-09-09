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
    private LocalDateTime time;
    private String password;
}
