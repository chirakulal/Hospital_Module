package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;

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
//    // List<String> getAllNames(Specialization specialization);
//
//     List<String> getTime();
//
//     boolean assignSlotToDoctor(String doctorName, String timeSlot);
//
   Long countDoctorEmail(String email);


}
