package com.xworkz.module.restcontroller;

import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class PatientRestController {

    @Autowired
   private HospitalService hospitalService;

    @GetMapping(value = {"CheckPatientPhoneNumber", "CheckPatientPhoneNumber/{CheckPhoneNumber}"})
    @ResponseBody
    public String checkPatientPhoneNumber(@PathVariable(required = false) String CheckPhoneNumber) {
        if (CheckPhoneNumber == null) {
            return ""; // No number entered, skip DB call
        }

        log.info("Checking phone number: {}", CheckPhoneNumber);

        int count = hospitalService.countPatientPhoneNumber(CheckPhoneNumber);
        log.info("Count: {}", count);

        return count == 0 ? "" : "Phone Number already exist";
    }

    @GetMapping(value = {"checkPatientEmail", "checkPatientEmail/{email}"})
    @ResponseBody
    public String checkPatientDoctorEmailCount(@PathVariable(required = false) String email) {
        if (email == null || email.trim().isEmpty()) {
            return ""; // just return empty, don't check DB
        }

        log.info("Checking email: {}", email);
        int count = hospitalService.countPatientEmail(email);
        return count > 0 ? "Email already exist" : "";
    }
}
