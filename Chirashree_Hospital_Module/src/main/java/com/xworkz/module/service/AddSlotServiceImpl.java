package com.xworkz.module.service;

import com.xworkz.module.constant.Specialization;
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

    @Override
    public List<String> getAllNames(Specialization specialization) {
        return hospitalRepo.getAllNames(specialization);
    }

    @Override
    public List<String> getTime() {
        return hospitalRepo.getTime();
    }

    @Override
    public boolean assignSlotToDoctor(String doctorName, String timeSlot) {
        return hospitalRepo.assignSlotToDoctor(doctorName,timeSlot);
    }
}
