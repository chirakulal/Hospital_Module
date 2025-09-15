package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.entity.DoctorEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public interface HospitalService {

    int emailCount(String email);

    boolean sendOtp(String email);

     boolean checkOtp(String otp, String email);

    int getRemainingCooldownSeconds(String email);

    boolean saveData(DoctorDTO doctorDTO);

    int countLastName(String lastName);

    int countPhoneNumber(String phone);
}
