package com.xworkz.module.service;

import com.xworkz.module.dto.PatientDTO;
import com.xworkz.module.dto.RegistrationIdGenerator;
import com.xworkz.module.entity.*;
import com.xworkz.module.repository.HospitalRepo;
import com.xworkz.module.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    public PatientServiceImpl() {
        log.info("Created \t" + this.getClass().getSimpleName());
    }

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @Autowired
    private EmailService emailService;

    private String uploadDir = "D:\\chiraimage\\HospitalProject\\ImageFolder";
    private Path symptomPath = Paths.get("D:\\chiraimage\\HospitalProject\\ImageFolder\\symptoms");


    @Override
    public boolean savePatientData(MultipartFile profileImage, List<MultipartFile> symptomImage, PatientDTO patientDTO) throws IOException {
        PatientEntity patientEntity = new PatientEntity();
        log.info("Running savePatientData for patientDTO: {}", patientDTO);

        // --- Validate mandatory fields ---
        if (patientDTO.getFirstName() == null || patientDTO.getEmail() == null ||
                patientDTO.getAppointmentDate() == null || patientDTO.getBloodType() == null) {
            throw new RuntimeException("Missing mandatory patient fields");
        }

        // --- Validate foreign keys ---
        DoctorEntity doctorEntity = doctorDetailsService.findByFullName(patientDTO.getDoctor());
        if (doctorEntity == null) {
            throw new RuntimeException("Doctor not found: " + patientDTO.getDoctor());
        }

        TimeSlotEntity timeSlotEntity = doctorDetailsService.getTImeSlotIdByTime(patientDTO.getSlot());
        if (timeSlotEntity == null) {
            throw new RuntimeException("Time slot not found: " + patientDTO.getSlot());
        }

        // --- Handle profile image ---
        String profileExt = FilenameUtils.getExtension(profileImage.getOriginalFilename());
        String profileFileName = patientDTO.getFirstName() + "_" + System.currentTimeMillis() + "." + profileExt;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        Path profilePath = uploadPath.resolve(profileFileName);
        Files.copy(profileImage.getInputStream(), profilePath);

        PatientProfileEntity profileEntity = new PatientProfileEntity();
        profileEntity.setOriginalImageName(profileImage.getOriginalFilename());
        profileEntity.setSavedName(profileFileName);
        profileEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        profileEntity.setFilePath(profilePath.toString());
        profileEntity.setFileSize(profileImage.getSize());
        profileEntity.setFileType(profileImage.getContentType());
        profileEntity.setPatientEntity(patientEntity);


        // --- Handle symptom images ---
        List<PatientSymptomsEntity> symptomEntities = new ArrayList<>();
        if (!Files.exists(symptomPath)) Files.createDirectories(symptomPath);

        if (symptomImage != null && !symptomImage.isEmpty()) {
            for (MultipartFile file : symptomImage) {
                String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                String symptomFileName = patientDTO.getFirstName() + "_symptom_" + System.currentTimeMillis() + "_" + Math.random() + "." + ext;
                Path symptomFileLocation = symptomPath.resolve(symptomFileName);
                Files.copy(file.getInputStream(), symptomFileLocation);

                PatientSymptomsEntity symptomEntity = new PatientSymptomsEntity();
                symptomEntity.setImageOriginalName(file.getOriginalFilename());
                symptomEntity.setImageName(symptomFileName);
                symptomEntity.setImagePath(symptomFileLocation.toString());
                symptomEntity.setSize(file.getSize());
                symptomEntity.setPatientEntity(patientEntity);

                symptomEntities.add(symptomEntity);
            }
        }
        // --- Map DTO to entity ---

        patientEntity.setFirstName(patientDTO.getFirstName());
        patientEntity.setLastName(patientDTO.getLastName());
        patientEntity.setAge(patientDTO.getAge());
        patientEntity.setEmail(patientDTO.getEmail());
        patientEntity.setPhone(patientDTO.getPhone());
        patientEntity.setBloodType(patientDTO.getBloodType());
        patientEntity.setAppointmentDate(patientDTO.getAppointmentDate());
        patientEntity.setHealthConcern(patientDTO.getHealthConcern());
        patientEntity.setSpecializationName(patientDTO.getSpecializationName());
        patientEntity.setDoctor(doctorEntity);
        patientEntity.setSlot(timeSlotEntity);
        patientEntity.setPatientProfileEntity(profileEntity);
        patientEntity.setPatientSymtomsImageEntityList(symptomEntities);

        // --- Generate unique registration ID ---
        patientEntity.setRegistrationId(generateUniqueRegistrationId(patientEntity.getFirstName()));



        // --- Save patient ---
        boolean result = hospitalRepo.savePatientData(patientEntity);
        if (result) {
            log.info("Patient data saved successfully for {}", patientEntity.getRegistrationId());
            emailService.sendPatientAppointmentEmail(patientEntity);
            return true;
        }

        return false;
    }

    // --- Correct unique registration ID check ---
    private String generateUniqueRegistrationId(String firstName) {
        final int MAX_ATTEMPTS = 5;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            String regId = RegistrationIdGenerator.generateRegistrationId(firstName);
            if (hospitalRepo.findByRegistrationId(regId) == null) {
                log.info("Generated unique registration ID: {}", regId);
                return regId;
            }
            log.warn("Registration ID collision: {}. Retrying...", regId);
        }
        throw new RuntimeException("Could not generate unique patient registration ID after " + MAX_ATTEMPTS + " attempts.");
    }
}
// NOTE: The 'registerNewPatient' method below was incomplete and seems redundant
// with savePatientData, so it should generally be removed unless it serves a
// different purpose. I will comment it out/remove it.
    /*
    public PatientEntity registerNewPatient(PatientEntity patientEntity) {
        // ... (REMOVE THIS METHOD OR FIX IT TO USE THE HELPER METHOD ABOVE)
    }
    */
//


