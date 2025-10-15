package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



public interface HospitalRepo {

    Long countEmail(String email);

    boolean updateOTp(String email, LocalDateTime time, String otp);

    HospitalEntity getEmail(String email);

    boolean saveSpecializationData(SpecializationEntity specializationEntity);

    List<String> getAllSpecializations();

    boolean saveData(DoctorEntity doctorEntity);
//
//    Long countLastName(String lastName);
//
     Long countPhoneNumber(String phone);

     boolean isTimeSlotExist(String specializationName, LocalTime startTime,LocalTime endTime);
//
   boolean saveTimeSlots(SlotEntity slotEntity);
//
List<String> getAllNames(String specialization);
//
       List<String> getTime(String specialization);
     List<String> getAvailableTimeForDoctor(String specialization, int doctorId);

    DoctorEntity findByFullName(String doctorName);
//
   boolean assignSlotToDoctor(TimeSlotEntity timeSlotEntity);
//
   Long countDoctorEmail(String email);

    List<DoctorEntity> getAllDoctors();

    DoctorEntity getAllDoctorsByEmail(String email);

    DoctorEntity getAllDoctorsById(int id);

    boolean updateDoctor(MultipartFile file,DoctorEntity doctorEntity);

    boolean DeleteDoctorByEmail(String email);

    List<String> getTimeSlotByEmail(String email);

    boolean savePatientData(PatientEntity patientEntity);

    PatientEntity getPatientByEmail(String email);

    TimeSlotEntity getTImeSlotIdByTime(String time);

    PatientEntity findByRegistrationId(String registrationId);

    Long countPatientPhoneNumber(String phone);
    Long countPatientEmail(String email);







}
