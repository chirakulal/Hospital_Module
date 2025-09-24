package com.xworkz.module.controller;


import com.xworkz.module.constant.BloodType;
import com.xworkz.module.service.DoctorDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PatientRegistrationController {


    @Autowired
    private DoctorDetailsService doctorDetailsService;


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
}
