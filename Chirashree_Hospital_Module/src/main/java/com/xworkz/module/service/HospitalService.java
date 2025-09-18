package com.xworkz.module.service;

import com.xworkz.module.constant.Specialization;
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

    int countPhoneNumber(long phone);

    boolean saveTimeSlot(TimeSlotDTO timeSlotDTO);

    List<String> getAllNames(Specialization specialization);

    List<String> getTime();

    boolean assignSlotToDoctor(String doctorName, String timeSlot);

    int countDoctorEmail(String email);
}
