package com.xworkz.module.service;

import com.xworkz.module.dto.TimeSlotDTO;
import com.xworkz.module.entity.TimeEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SlotServiceIMpl implements SlotService {

    public SlotServiceIMpl(){
        System.out.println("Created SlotServiceIMpl");
    }

    @Autowired
    private HospitalRepo hospitalRepo;


    @Override
    public boolean saveTimeSlot(TimeSlotDTO timeSlotDTO) {

            TimeEntity time = new TimeEntity();
            time.setStartTime(timeSlotDTO.getStartTime());
            time.setEndTime(timeSlotDTO.getEndTime());

            return hospitalRepo.saveTimeSlots(time);

    }
}
