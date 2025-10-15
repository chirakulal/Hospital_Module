package com.xworkz.module.restcontroller;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.SlotDTO;
import com.xworkz.module.service.DoctorDetailsService;
import com.xworkz.module.service.HospitalService;
import com.xworkz.module.service.SlotService;
import com.xworkz.module.service.UpdateDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class ValidationRestController {
    public ValidationRestController(){
        log.info("rest controller.........");
    }
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @Autowired
    private UpdateDetailsServiceImpl updateDetailsService;

    @Autowired
    private SlotService slotservice;

    @GetMapping("checkEmail/{email}")
    public String checkEmailCount(@PathVariable String email){
        log.info(email);
        int count = hospitalService.emailCount(email);
        log.info(String.valueOf(count));
        if(count==1) return "";
        else return "Email does not exist";

    }

//  @GetMapping("CheckLastName/{lastname}")
//    public String checkLastNameCount(@PathVariable String lastName){
//        log.info(lastName);
//        int count = hospitalService.countLastName(lastName);
//        log.info(String.valueOf(count));
//        if(count==0) return "";
//        else return "Email does not exist";
//
//    }
//
//
@GetMapping(value = {"CheckPhoneNumber", "CheckPhoneNumber/{CheckPhoneNumber}"})
@ResponseBody
public String checkPhoneNumber(@PathVariable(required = false) String CheckPhoneNumber) {
    if (CheckPhoneNumber == null) {
        return ""; // No number entered, skip DB call
    }

    log.info("Checking phone number: {}", CheckPhoneNumber);

    int count = hospitalService.countPhoneNumber(CheckPhoneNumber);
    log.info("Count: {}", count);

    return count == 0 ? "" : "Phone Number already exist";
}

    @GetMapping(value = {"checkDoctorEmail", "checkDoctorEmail/{email}"})
    @ResponseBody
    public String checkDoctorEmailCount(@PathVariable(required = false) String email) {
        if (email == null || email.trim().isEmpty()) {
            return ""; // just return empty, don't check DB
        }

        log.info("Checking email: {}", email);
        int count = hospitalService.countDoctorEmail(email);
        return count > 0 ? "Email already exist" : "";
    }


    @GetMapping("fetchDoctor/{specialization}")
    @ResponseBody
    public String checkDoctor(@PathVariable String specialization, Model model) {
        log.info(specialization);
      List<DoctorDTO> dtos = updateDetailsService.getAllDoctors();//same name may cause problem
        log.info("dtos size: {}", dtos.size());
        log.info("dtos: {}", dtos);


        if(dtos.isEmpty()){

            return "No Doctors available";
        }
        List<String> matchedDoctors = new ArrayList<>();

        for (DoctorDTO dto : dtos) {
            if (specialization.equals(dto.getSpecializationName())) {
                matchedDoctors.add(dto.getFirstName()+" "+dto.getLastName()+"|"+dto.getEmail());
            }
        }
        if (matchedDoctors.isEmpty()) {
            return "No doctors";
        } else {
            return String.join(",", matchedDoctors);
        }

    }

    @GetMapping("getTimeSlotByEmail/{email}")
    public String getTimeSlotByEmail(@PathVariable String email){
        log.info(email);
        List<String> timeSlot = doctorDetailsService.getTimeSlotByEmail(email);

        if (timeSlot == null || timeSlot.isEmpty()) {
            log.info("No time slots found for email: {}", email);
            return "No Time Slot Assigned";
        }
        String resultString = String.join(",", timeSlot);
        log.info("Returning time slot string: {}", resultString);
        return resultString;
    }

    @PostMapping("/api/slot/check")
    public String checkTimeSlotExist(
            @RequestParam String specializationName,
            @RequestParam String startTime,
            @RequestParam String endTime) {

        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        boolean exists = slotservice.checkSlotExist(specializationName, start, end);
        return exists ? "exists" : "not_exists";
    }

}
