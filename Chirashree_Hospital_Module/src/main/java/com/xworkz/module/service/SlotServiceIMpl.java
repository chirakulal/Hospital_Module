package com.xworkz.module.service;

import com.xworkz.module.dto.SlotDTO;
import com.xworkz.module.entity.SlotEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SlotServiceIMpl implements SlotService {

    public SlotServiceIMpl() {
        System.out.println("Created SlotServiceIMpl");
    }

    @Autowired
    private HospitalRepo hospitalRepo;

    @Override
    public List<String> getAllNames() {
        List<String> list = hospitalRepo.getAllSpecializations();
        if (list != null && !list.isEmpty()) {
            log.info("List is found in service" + list.size());
            return list;
        } else {
            log.info("List is not found in service");
            return Collections.emptyList(); // Return an empty list instead of null
        }
    }
    @Override
    public boolean checkSlotExist(String specialization, LocalTime startTime, LocalTime endTime) {
        return hospitalRepo.isTimeSlotExist(specialization,startTime,endTime);
    }


    @Override
    public boolean saveTimeSlot(SlotDTO slotDTO) {

        SlotEntity slotEntity = new SlotEntity();
        slotEntity.setSpecializationName(slotDTO.getSpecializationName());
        slotEntity.setStartTime(slotDTO.getStartTime());
        slotEntity.setEndTime(slotDTO.getEndTime());
        boolean result = hospitalRepo.saveTimeSlots(slotEntity);
        log.info("Data saved in entity" + result);
        return result;
    }

}
