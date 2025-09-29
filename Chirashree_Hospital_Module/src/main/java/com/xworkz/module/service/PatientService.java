package com.xworkz.module.service;

import com.xworkz.module.dto.PatientDTO;
import org.springframework.stereotype.Service;


public interface PatientService {
    boolean savePatientData(PatientDTO patientDTO);
}
