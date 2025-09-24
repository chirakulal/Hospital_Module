package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;

import java.io.IOException;
import java.util.List;

public interface DoctorDetailsService {


    List<String> getAllNames();

   boolean saveData(DoctorDTO doctorDTO) throws IOException;

   String getTimeSlotByEmail(String email);


}
