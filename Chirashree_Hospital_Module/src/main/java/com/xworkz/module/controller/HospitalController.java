package com.xworkz.module.controller;


import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.xworkz.module")
@RequestMapping("/")
@Slf4j
public class HospitalController {



    public HospitalController(){
        log.info("Controller.........");
    }

    @Autowired
    private HospitalService hospitalService;


    @GetMapping("admin")
    public ModelAndView adminPage(ModelAndView modelAndView){

        modelAndView.setViewName("AdminLogin");
        return modelAndView;
    }

    @PostMapping("sendOtp")
    public ModelAndView sendOtp(ModelAndView modelAndView, @PathVariable String email){

        try {
            hospitalService.sendOtp(email);
            modelAndView.addObject("message", "OTP sent successfully to " + email);
        } catch (Exception e) {
            modelAndView.addObject("message", "Failed to send OTP");
        }
        modelAndView.setViewName("adminLogin");


        return modelAndView;
    }


}
