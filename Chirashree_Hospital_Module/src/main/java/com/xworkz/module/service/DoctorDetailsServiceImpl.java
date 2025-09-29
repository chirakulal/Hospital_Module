package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.ImageEntity;
import com.xworkz.module.entity.TimeSlotEntity;
import com.xworkz.module.repository.HospitalRepo;
import com.xworkz.module.service.email.EmailService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DoctorDetailsServiceImpl implements DoctorDetailsService {

    public DoctorDetailsServiceImpl() {
        log.info("Created \t" + this.getClass().getSimpleName());
    }

    private String uploadDir = "D:\\chiraimage\\HospitalProject\\DoctorProfile";

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private EmailService emailService;


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


    @Override
    public boolean saveData(MultipartFile file,DoctorDTO doctorDTO) throws IOException {

        // 1. Save the image file to the server

        String originalExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = doctorDTO.getFirstName() + "_" + System.currentTimeMillis() + "." + originalExtension;
        Path uploadPath = Paths.get(uploadDir);

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Copy the file to the target location
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // 2. Create and populate the ImageEntity
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setOriginalImageName(file.getOriginalFilename());
        imageEntity.setFileType(file.getContentType());
        imageEntity.setFileSize(file.getSize());
        imageEntity.setFilePath(filePath.toString());
        imageEntity.setSavedName(fileName);
        imageEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        imageEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        imageEntity.setCreatedBy(doctorDTO.getCreatedBy()); // Assuming this is set by a security context




        DoctorEntity doctorEntity = new DoctorEntity();


        doctorEntity.setFirstName(doctorDTO.getFirstName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setEmail(doctorDTO.getEmail());
        doctorEntity.setPhone(doctorDTO.getPhone());
        doctorEntity.setSpecializationName(doctorDTO.getSpecializationName());
        doctorEntity.setExperience(doctorDTO.getExperience());
        doctorEntity.setAddress(doctorDTO.getAddress());
        doctorEntity.setGender(doctorDTO.getGender());
        doctorEntity.setDegree(doctorDTO.getDegree());
        doctorEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        doctorEntity.setCreatedBy(doctorDTO.getCreatedBy());
        doctorEntity.setProfilePicture(imageEntity);




        boolean saved = hospitalRepo.saveData(doctorEntity);
        if (saved) {
            log.info("Doctor data saved successfully");
            emailService.sendDoctorConfirmationEmail(doctorDTO.getFirstName() + " " + doctorDTO.getLastName(), doctorDTO.getEmail());
            return true;
        } else {
            log.error("Failed to save doctor data");
            return false;
        }


    }

    @Override
    public List<String> getTimeSlotByEmail(String email) {
        List<String> timeSlot = hospitalRepo.getTimeSlotByEmail(email);
        if (timeSlot != null) {
            log.info("Time slot found for email {}: {}", email, timeSlot);
            return timeSlot;
        } else {
            log.info("No time slot found for email {}", email);
            return Collections.emptyList();

        }
    }

    @Override
    public DoctorEntity findByFullName(String doctorName) {
        DoctorEntity doctor = hospitalRepo.findByFullName(doctorName);
        if (doctor != null) {
            log.info("Doctor ID found for name {}: {}", doctorName, doctor.getId());
            return doctor;
        } else {
            log.info("No Doctor ID found for name {}", doctorName);
            return null;
        }
    }

    @Override
    public TimeSlotEntity getTImeSlotIdByTime(String time) {
        TimeSlotEntity timeSlotEntity = hospitalRepo.getTImeSlotIdByTime(time);
        if (timeSlotEntity != null) {
            log.info("Time Slot ID found for time {}: {}", time,   timeSlotEntity.getId());
            return timeSlotEntity;
        } else {
            log.info("No Time Slot ID found for time {}", time);
            return null;
        }
    }
}