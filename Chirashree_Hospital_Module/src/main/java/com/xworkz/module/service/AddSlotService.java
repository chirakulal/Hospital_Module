package com.xworkz.module.service;

import java.util.List;

public interface AddSlotService {

    List<String> getAllNames(String specialization);

    List<String> getTime(String specialization);
//
   boolean assignSlotToDoctor(String doctorName, String timeSlot);
}
