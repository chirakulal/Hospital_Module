package com.xworkz.module.service;

import com.xworkz.module.dto.PatientDTO;
import com.xworkz.module.entity.PatientEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface PatientService {
    boolean savePatientData(MultipartFile profileImage, List<MultipartFile> symptomImage,PatientDTO patientDTO) throws IOException;

}
