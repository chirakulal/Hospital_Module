package com.xworkz.module.service;

import com.xworkz.module.dto.SlotDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface SlotService {

    List<String> getAllNames();

    boolean checkSlotExist(String specialization, LocalTime startTime, LocalTime endTime);


    boolean saveTimeSlot(SlotDTO slotDTO);
}
