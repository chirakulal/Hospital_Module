package com.xworkz.module.controller;


import com.xworkz.module.dto.HospitalDTO;
import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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
    public ModelAndView sendOtp(ModelAndView modelAndView,
                                @RequestParam String email,
                                HospitalDTO hospitalDTO,
                                HttpSession session) {
        hospitalDTO.setEmail(email);
        session.setAttribute("loginEmailForOtp", email);

        try {
            boolean result = hospitalService.sendOtp(email);
            if (result) {

                session.setAttribute("loginEmailForOtp", email);
                session.setAttribute("otpSentTime", LocalDateTime.now());

                modelAndView.addObject("success", "OTP sent successfully!");
                modelAndView.addObject("dto", hospitalDTO);


                modelAndView.addObject("remainingSeconds", 120);
                modelAndView.setViewName("verifyOtp");
                log.info("OTP sent to {}", email);
            } else {
                modelAndView.addObject("error", "Failed to send OTP.");
                modelAndView.addObject("dto", hospitalDTO);
                modelAndView.setViewName("AdminLogin");
            }
        } catch (Exception e) {
            log.error("Error while sending OTP", e);
            modelAndView.addObject("error", "Failed to send OTP.");
            modelAndView.addObject("dto", hospitalDTO);
            modelAndView.setViewName("AdminLogin");
        }

        return modelAndView;
    }


    @PostMapping("verifyOtp")
    public ModelAndView verifyOtp(ModelAndView modelAndView,
                                  @RequestParam String otp,
                                  @RequestParam String email,
                                  HttpSession session) {

        LocalDateTime sentTime = (LocalDateTime) session.getAttribute("otpSentTime");
        if (sentTime == null) {
            modelAndView.addObject("error", "Session expired. Please request OTP again.");
            modelAndView.setViewName("AdminLogin");
            return modelAndView;
        }

        boolean check = hospitalService.checkOtp(otp, sentTime, email);
        if (!check) {
            long secondsPassed = java.time.Duration.between(sentTime, LocalDateTime.now()).getSeconds();
            int remaining = (int) Math.max(0, 120 - secondsPassed);

            modelAndView.addObject("error", "Invalid or expired OTP. Please try again!");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", remaining);
            modelAndView.setViewName("verifyOtp");
        } else {
            session.removeAttribute("otpSentTime");
            session.removeAttribute("loginEmailForOtp");

            modelAndView.addObject("success", "OTP verified successfully!");
            modelAndView.setViewName("Home");
        }

        return modelAndView;
    }


    // Step 3: Resend OTP
    @PostMapping("resendOtp")
    public ModelAndView resendOtp(ModelAndView modelAndView, HttpSession session) {
        String email = (String) session.getAttribute("loginEmailForOtp");
        hospitalService.sendOtp(email);

        modelAndView.addObject("email", email);
        modelAndView.addObject("success", "New OTP sent successfully!");
        modelAndView.addObject("remainingSeconds", 120);
        modelAndView.setViewName("verifyOtp");
        return modelAndView;
    }

    @GetMapping("doctors")
    public ModelAndView DoctorPage(ModelAndView modelAndView){

        modelAndView.setViewName("DoctorDetails");
        return modelAndView;
    }

}
