package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.UpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UpdateDetailsService {

    List<DoctorDTO>  getAllDoctors();

    DoctorDTO getDoctorByEmail(String email);

    DoctorDTO getDoctorById(int id);

    boolean UpdateDoctor(MultipartFile file,UpdateDTO doctorDTO);

    boolean DeleteDoctorByEmail(String email);
}
