package com.xworkz.module.repository;

import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.HospitalEntity;
import com.xworkz.module.entity.TimeEntity;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface HospitalRepo {

    Long countEmail(String email);

    boolean updateOTp(String email, LocalDateTime time, String otp);

    HospitalEntity getEmail(String email);

    boolean saveData(DoctorEntity doctorEntity);

    Long countLastName(String lastName);

     Long countPhoneNumber(String phone);

     boolean saveTimeSlots(TimeEntity timeEntity);

     List<String> getAllNames();

     List<LocalTime> getTime();


}
