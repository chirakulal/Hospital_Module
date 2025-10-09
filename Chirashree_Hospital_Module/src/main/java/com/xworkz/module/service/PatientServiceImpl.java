package com.xworkz.module.service;

import com.xworkz.module.dto.PatientDTO;
import com.xworkz.module.dto.RegistrationIdGenerator;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.PatientEntity;
import com.xworkz.module.entity.TimeSlotEntity;
import com.xworkz.module.repository.HospitalRepo;
import com.xworkz.module.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;


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

    @Override
    public boolean savePatientData(PatientDTO patientDTO) {
        log.info("Running savePatientData in service" + patientDTO);
        DoctorEntity doctorEntity = doctorDetailsService.findByFullName(patientDTO.getDoctor());
        TimeSlotEntity timeSlotEntity = doctorDetailsService.getTImeSlotIdByTime(patientDTO.getSlot());
        log.info("Fetched doctor entity" + doctorEntity);
        log.info("Fetched time slot entity" + timeSlotEntity);

        PatientEntity patientEntity = new PatientEntity();
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
        log.info("Converted entity data" + patientEntity);

        patientEntity.setRegistrationId(
                generateUniqueRegistrationId(patientEntity.getFirstName())
        );

        boolean result = hospitalRepo.savePatientData(patientEntity);

        if (result) {
            log.info("Patient data saved successfully");
           emailService.sendPatientAppointmentEmail(patientEntity);
           
            return true;
        }


        return false;
    }

    private String generateUniqueRegistrationId(String firstName) {
        final int MAX_ATTEMPTS = 5;
        String regId = null;

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            // Generate a potential ID
            regId = RegistrationIdGenerator.generateRegistrationId(firstName);

            // Check if it already exists in the database
            // NOTE: Requires a findByRegistrationId method on HospitalRepo
            PatientEntity existingPatient = hospitalRepo.getPatientByEmail(regId);

            if (existingPatient == null) {
                // ID is unique
                log.info("Successfully generated unique Reg ID: {}", regId);
                return regId;
            }

            // If ID exists, log and loop to generate a new one
            log.warn("Registration ID collision detected: {}. Retrying...", regId);
        }

        // If all attempts fail, throw an exception
        log.error("Failed to generate a unique registration ID after {} attempts.", MAX_ATTEMPTS);
        throw new RuntimeException("Could not generate a unique patient registration ID.");
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

