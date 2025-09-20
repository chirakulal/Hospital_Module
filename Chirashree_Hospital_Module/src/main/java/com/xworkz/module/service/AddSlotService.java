package com.xworkz.module.service;

import com.xworkz.module.constant.Specialization;

import java.util.List;

public interface AddSlotService {

    List<String> getAllNames(Specialization specialization);

    List<String> getTime();

    boolean assignSlotToDoctor(String doctorName, String timeSlot);
}
