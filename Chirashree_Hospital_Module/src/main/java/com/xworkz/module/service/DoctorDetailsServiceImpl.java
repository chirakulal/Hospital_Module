package com.xworkz.module.service;

import com.xworkz.module.constant.Specialization;
import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DoctorDetailsServiceImpl implements DoctorDetailsService{

    public DoctorDetailsServiceImpl(){
        log.info("Created \t"+this.getClass().getSimpleName());
    }

    @Autowired
    private  HospitalRepo hospitalRepo;

    @Override
    public boolean saveData(DoctorDTO doctorDTO) {
        DoctorEntity doctorEntity = new DoctorEntity();


        doctorEntity.setFirstName(doctorDTO.getFirstName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setEmail(doctorDTO.getEmail());
        doctorEntity.setPhone(doctorDTO.getPhone());
        doctorEntity.setSpecialization(doctorDTO.getSpecialization());
        doctorEntity.setExperience(doctorDTO.getExperience());
        doctorEntity.setImage(doctorDTO.getImage());
        doctorEntity.setAddress(doctorDTO.getAddress());
        doctorEntity.setGender(doctorDTO.getGender());
        doctorEntity.setDegree(doctorDTO.getDegree());




        return hospitalRepo.saveData(doctorEntity);
    }

}
