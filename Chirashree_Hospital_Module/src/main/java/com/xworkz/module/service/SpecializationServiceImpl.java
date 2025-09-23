package com.xworkz.module.service;

import com.xworkz.module.dto.SpecializationDTO;
import com.xworkz.module.entity.SpecializationEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SpecializationServiceImpl implements SpecializationService{

    @Autowired
    private HospitalRepo hospitalRepo;


    @Override
    public boolean saveSpecialization(SpecializationDTO specializationDTO) {
        SpecializationEntity specializationEntity = new SpecializationEntity();
        specializationEntity.setSpecialization_name(specializationDTO.getName());
        specializationEntity.setCreatedBy(specializationDTO.getCreatedBy());
        specializationEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        specializationEntity.setUpdatedBy(specializationDTO.getUpdatedBy());
        specializationEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return hospitalRepo.saveSpecializationData(specializationEntity) ; // Return true if saved successfully
    }
    @Override
    public List<String> getAllNames() {
        List<String> list = hospitalRepo.getAllSpecializations();
        if (list != null && !list.isEmpty()) {
            log.info("List is found in service" + list.size());
            return list;
        } else {
            log.info("List is not found in service");
            return Collections.emptyList(); // Return an empty list instead of null
        }
    }

}
