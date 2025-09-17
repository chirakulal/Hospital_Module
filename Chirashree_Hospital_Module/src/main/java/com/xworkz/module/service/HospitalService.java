package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.TimeSlotDTO;
import com.xworkz.module.entity.DoctorEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface HospitalService {

    int emailCount(String email);

    boolean sendOtp(String email);

     boolean checkOtp(String otp, String email);

    int getRemainingCooldownSeconds(String email);

    boolean saveData(DoctorDTO doctorDTO);

    int countLastName(String lastName);

    int countPhoneNumber(String phone);

    boolean saveTimeSlot(TimeSlotDTO timeSlotDTO);

    List<String> getAllNames();

    List<String> getTime();
}
