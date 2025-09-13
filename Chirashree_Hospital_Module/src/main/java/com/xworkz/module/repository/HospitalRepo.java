package com.xworkz.module.repository;

import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.HospitalEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


public interface HospitalRepo {

    Long countEmail(String email);

    boolean updateOTp(String email, LocalDateTime time, String otp);

    HospitalEntity getEmail(String email);

    boolean saveData(DoctorEntity doctorEntity);
}
