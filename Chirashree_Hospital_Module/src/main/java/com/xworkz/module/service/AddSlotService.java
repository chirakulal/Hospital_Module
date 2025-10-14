package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.entity.DoctorEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AddSlotService {

    List<String> getAllNames(String specialization);

    List<String> getTime(String specialization);

    List<String> getAvailableTimeForDoctor(String specialization, String doctorName);
    //
  boolean assignSlotToDoctor(String doctorName, String timeSlot);
}
