package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalEntity {


    private int id;
    private String email;
    private int otp;
    private LocalDateTime time;
    private String password;
}
