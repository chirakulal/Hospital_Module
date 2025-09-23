package com.xworkz.module.service;

import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return hospitalRepo.getAllNames(specialization);
    }
//
    @Override
    public List<String> getTime(String specialization) {
        return hospitalRepo.getTime(specialization);
    }
//
    @Override
    public boolean assignSlotToDoctor(String doctorName, String timeSlot) {
        boolean updated = hospitalRepo.assignSlotToDoctor(doctorName, timeSlot);
        if (updated) {
            log.info("Slot assigned to doctor successfully");
            return true;
        }
        log.info("Failed to assign slot to doctor");
        return false;
    }
}
