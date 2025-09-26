package com.xworkz.module.service;

import com.xworkz.module.dto.SpecializationDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SpecializationService {


    boolean saveSpecialization(SpecializationDTO specializationDTO);
    List<String> getAllNames();
}
