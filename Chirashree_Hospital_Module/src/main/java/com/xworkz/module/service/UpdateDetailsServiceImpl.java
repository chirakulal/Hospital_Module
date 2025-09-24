package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorResponseDTO;
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
    public List<DoctorResponseDTO> getAllDoctors() {
        List<DoctorEntity> doctorEntity = hospitalRepo.getAllDoctors();


        if(doctorEntity == null){
            return null;
        }
        List<DoctorResponseDTO> dtos = new ArrayList<>();
        for(DoctorEntity entity : doctorEntity){
            DoctorResponseDTO doctorUpdateDTO = new DoctorResponseDTO();
            BeanUtils.copyProperties(entity, doctorUpdateDTO);
            if(entity.getProfilePicture().getFilePath()!=null) {
                doctorUpdateDTO.setImages(entity.getProfilePicture().getSavedName());
            }

           if(entity.getDegree()!=null){
              List<String> degrees = entity.getDegree();
              String[] degreeArray = degrees.toArray(new String[0]);
              doctorUpdateDTO.setDegree(degreeArray);

           }


            dtos.add(doctorUpdateDTO);
        }
        return dtos;
    }

    @Override
    public DoctorResponseDTO getDoctorById(int id) {
        DoctorEntity doctorEntity = hospitalRepo.getAllDoctorsById(id);
        if(doctorEntity != null){
            DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
            BeanUtils.copyProperties(doctorEntity, doctorDTO);
            if(doctorEntity.getDegree()!=null){
                List<String> degrees = doctorEntity.getDegree();
                String[] degreeArray = degrees.toArray(new String[0]);
                doctorDTO.setDegree(degreeArray);
            }
            if(doctorEntity.getProfilePicture().getFilePath()!=null) {
                doctorDTO.setImages(doctorEntity.getProfilePicture().getSavedName());
            }
            return doctorDTO;
        }
        return null;
    }

    @Override
    public boolean UpdateDoctor(MultipartFile file,UpdateDTO doctorDTO) {
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
    public boolean DeleteDoctorById(int id) {
        log.info("Running DeleteDoctorById in UpdateDetailsServiceImpl with id: " + id);
        boolean isDeleted = hospitalRepo.DeleteDoctorById(id);
        log.info("Delete operation result: " + isDeleted);
        return isDeleted;
    }

    @Override
    public DoctorResponseDTO getDoctorByEmail(String email) {
        DoctorEntity doctorEntity = hospitalRepo.getAllDoctorsByEmail(email);
        if(doctorEntity != null){
            DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
            BeanUtils.copyProperties(doctorEntity, doctorDTO);
            if(doctorEntity.getDegree()!=null){
                List<String> degrees = doctorEntity.getDegree();
                String[] degreeArray = degrees.toArray(new String[0]);
                doctorDTO.setDegree(degreeArray);
            }
            if(doctorEntity.getProfilePicture().getFilePath()!=null) {
                doctorDTO.setImages(doctorEntity.getProfilePicture().getSavedName());
            }
            return doctorDTO;
        }
        return null;
    }
}
