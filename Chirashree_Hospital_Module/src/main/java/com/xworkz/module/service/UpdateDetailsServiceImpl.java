package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.UpdateDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.repository.HospitalRepo;
import com.xworkz.module.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UpdateDetailsServiceImpl implements UpdateDetailsService{

    public UpdateDetailsServiceImpl(){
        log.info("Created \t"+this.getClass().getSimpleName());
    }

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private EmailService emailService;



    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<DoctorEntity> doctorEntity = hospitalRepo.getAllDoctors();


        if(doctorEntity == null){
            return null;
        }
        List<DoctorDTO> dtos = new ArrayList<>();
        for(DoctorEntity entity : doctorEntity){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(entity, doctorDTO);
            if(entity.getProfilePicture().getFilePath()!=null) {
                doctorDTO.setImages(entity.getProfilePicture().getSavedName());
            }
            dtos.add(doctorDTO);
        }
        return dtos;
    }

    @Override
    public DoctorDTO getDoctorById(int id) {
        DoctorEntity doctorEntity = hospitalRepo.getAllDoctorsById(id);
        if(doctorEntity != null){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorEntity, doctorDTO);

            if(doctorEntity.getProfilePicture().getFilePath()!=null) {
                doctorDTO.setImages(doctorEntity.getProfilePicture().getSavedName());
            }
            return doctorDTO;
        }
        return null;
    }

    @Override
    public boolean UpdateDoctor( MultipartFile file, UpdateDTO doctorDTO) {
        log.info("Running UpdateDoctor in UpdateDetailsServiceImpl");
        log.info("DoctorDTO: " + doctorDTO);
        DoctorEntity doctorEntity = new DoctorEntity();
        BeanUtils.copyProperties(doctorDTO, doctorEntity);
        log.info("DoctorEntity after copyProperties: " + doctorEntity);
        boolean isUpdated = hospitalRepo.updateDoctor(file,doctorEntity);
        log.info("Update operation result: " + isUpdated);
        if(isUpdated) {
            log.info("Doctor details updated successfully for ID: " + doctorEntity.getEmail());
            emailService.sendDoctorUpdateEmail(doctorDTO.getFirstName()+doctorDTO.getLastName(),doctorDTO.getEmail());

        } else {
            log.warn("Failed to update doctor details for ID: " + doctorEntity.getId());
        }
        return isUpdated;
    }

    @Override
    public boolean DeleteDoctorByEmail(String email) {
        log.info("Running DeleteDoctorById in UpdateDetailsServiceImpl with email: " + email);
        boolean isDeleted = hospitalRepo.DeleteDoctorByEmail(email);
        log.info("Delete operation result: " + isDeleted);
        return isDeleted;
    }

    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        DoctorEntity doctorEntity = hospitalRepo.getAllDoctorsByEmail(email);
        if(doctorEntity != null){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorEntity, doctorDTO);
            if(doctorEntity.getProfilePicture().getFilePath()!=null) {
                doctorDTO.setImages(doctorEntity.getProfilePicture().getSavedName());
            }
            return doctorDTO;
        }
        return null;
    }
}
