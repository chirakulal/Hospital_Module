package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
     Long countPhoneNumber(long phone);
//
   boolean saveTimeSlots(SlotEntity slotEntity);
//
List<String> getAllNames(String specialization);
//
       List<String> getTime(String specialization);
//
   boolean assignSlotToDoctor(String doctorName, String timeSlot);
//
   Long countDoctorEmail(String email);

    List<DoctorEntity> getAllDoctors();

    DoctorEntity getAllDoctorsByEmail(String email);

    DoctorEntity getAllDoctorsById(int id);

    boolean updateDoctor(MultipartFile file,DoctorEntity doctorEntity);

    boolean DeleteDoctorById(int id);

    String getTimeSlotByEmail(String email);



}
