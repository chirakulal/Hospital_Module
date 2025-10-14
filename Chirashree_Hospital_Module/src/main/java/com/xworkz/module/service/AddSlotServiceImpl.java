package com.xworkz.module.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.TimeSlotEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AddSlotServiceImpl implements AddSlotService {

    public AddSlotServiceImpl(){
        System.out.println("Created \t"+this.getClass().getSimpleName());
    }

    @Autowired
    private HospitalRepo hospitalRepo;
//
    @Override
    public List<String> getAllNames(String specialization) {
      List<String> doctorEntities =  hospitalRepo.getAllNames(specialization);

        return doctorEntities;
    }
//
    @Override
    public List<String> getTime(String specialization) {
        return hospitalRepo.getTime(specialization);
    }

    // In service implementation
    @Override
    public List<String> getAvailableTimeForDoctor(String specialization, String doctorName) {
        DoctorEntity doctor = hospitalRepo.findByFullName(doctorName); // Assumes hospitalRepo is used to find doctor
        if (doctor == null) return Collections.emptyList();
        // Calls the repository method with a long for doctorId
        return hospitalRepo.getAvailableTimeForDoctor(specialization, doctor.getId());
    }

    @Override
    public boolean assignSlotToDoctor(String doctorName, String timeSlot) {

        DoctorEntity doctorEntity = hospitalRepo.findByFullName(doctorName);
        if (doctorEntity == null) {
            log.info("Doctor not found with name: " + doctorName);
            return false;
        }

        TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
        timeSlotEntity.setDoctor(doctorEntity);
        timeSlotEntity.setTimeSlot(timeSlot);

        boolean updated = hospitalRepo.assignSlotToDoctor(timeSlotEntity);

        if (updated) {
            log.info("Slot assigned to doctor successfully");
            return true;
        }
        log.info("Failed to assign slot to doctor");
        return false;
    }
}
