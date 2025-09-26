package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface DoctorDetailsService {


    List<String> getAllNames();

   boolean saveData(MultipartFile file,DoctorDTO doctorDTO) throws IOException;

    List<String> getTimeSlotByEmail(String email);


}
