package com.xworkz.module.service;

import com.xworkz.module.dto.SlotDTO;

import java.util.List;

public interface SlotService {

    List<String> getAllNames();

    boolean saveTimeSlot(SlotDTO slotDTO);
}
