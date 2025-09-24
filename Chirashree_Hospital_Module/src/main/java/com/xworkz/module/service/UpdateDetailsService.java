package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorResponseDTO;
import com.xworkz.module.dto.UpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UpdateDetailsService {

    List<DoctorResponseDTO>  getAllDoctors();

    DoctorResponseDTO getDoctorByEmail(String email);

    DoctorResponseDTO getDoctorById(int id);

    boolean UpdateDoctor(MultipartFile file,UpdateDTO doctorDTO);

    boolean DeleteDoctorById(int id);
}
