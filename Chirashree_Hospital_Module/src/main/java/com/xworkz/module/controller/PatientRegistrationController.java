package com.xworkz.module.controller;


import com.xworkz.module.constant.BloodType;
import com.xworkz.module.dto.PatientDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.TimeSlotEntity;
import com.xworkz.module.service.DoctorDetailsService;
import com.xworkz.module.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PatientRegistrationController {


    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @Autowired
    private PatientService patientService;



    public PatientRegistrationController(){
        log.info("Created PatientRegistrationController");
    }



    @GetMapping("PatientRegistration")
    public ModelAndView onPatientRegistrationPage(ModelAndView modelAndView){

      List<String> specialization = doctorDetailsService.getAllNames();
        log.info("Running onPatientRegistration method");
        modelAndView.addObject("bloodTypes", BloodType.values());
        modelAndView.addObject("specializations",specialization );
        modelAndView.setViewName("Registration");
        return modelAndView;
    }

    @PostMapping("savePatient")
    public ModelAndView onSavePatient(@RequestParam("profile") MultipartFile profileImage,
                                      @RequestParam("symptomsImage") List<MultipartFile> patientSymtomsImage,
                                      ModelAndView modelAndView, PatientDTO patientDTO) throws IOException {
        log.info("PatientDTO data"+patientDTO);

        boolean saved = patientService.savePatientData(profileImage,patientSymtomsImage,patientDTO);
        if(saved){
            modelAndView.addObject("success","Patient Registered Successfully");
        }else {
            modelAndView.addObject("error","Failed to Register Patient");
        }
        modelAndView.addObject("bloodTypes", BloodType.values());
        modelAndView.addObject("specializations",doctorDetailsService.getAllNames() );
        log.info("Running onSavePatient method");
        modelAndView.setViewName("Registration");
        return modelAndView;
    }
}
