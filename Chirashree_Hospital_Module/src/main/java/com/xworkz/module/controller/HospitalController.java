package com.xworkz.module.controller;


import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
                                @RequestParam String email,HttpSession httpSession) {


        try {
            boolean result = hospitalService.sendOtp(email);
            if (result) {
                httpSession.setAttribute("loginEmail", email);
                modelAndView.addObject("success", "OTP sent successfully!");
                modelAndView.addObject("email",email);
                modelAndView.addObject("remainingSeconds", 120);
                modelAndView.setViewName("verifyOtp");
                log.info("OTP sent to {}", email);

            } else {
                modelAndView.addObject("error", "Failed to send OTP.");
                modelAndView.addObject("email",email);
                modelAndView.setViewName("AdminLogin");
            }
        } catch (Exception e) {
            log.error("Error while sending OTP", e);
            modelAndView.addObject("error", "Failed to send OTP.");
            modelAndView.addObject("email",email);
            modelAndView.setViewName("AdminLogin");
        }

        return modelAndView;
    }


    @PostMapping("verifyOtp")
    public ModelAndView verifyOtp(ModelAndView modelAndView,
                                  @RequestParam String otp,
                                  @RequestParam String email,
                                  HttpSession session) {

        if (session.getAttribute("loggedInUser") != null) {
            modelAndView.setViewName("redirect:/dashboard-success");
            return modelAndView;
        }

        boolean check = hospitalService.checkOtp(otp,  email);
        if (!check) {
         int remaining =   hospitalService.getRemainingCooldownSeconds(email);
            modelAndView.addObject("error", "Invalid or expired OTP. Please try again!");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", remaining);
            modelAndView.setViewName("verifyOtp");
        } else {
            session.setAttribute("loggedInUser", email);
            modelAndView.addObject("success", "OTP verified successfully!");
            modelAndView.setViewName("redirect:/dashboard-success");
        }

        return modelAndView;
    }


    // Step 3: Resend OTP
    @PostMapping("resendOtp")
    public ModelAndView resendOtp(ModelAndView modelAndView, @RequestParam String email) {
        int remainingSeconds = hospitalService.getRemainingCooldownSeconds(email);
        if (remainingSeconds > 0) {
            modelAndView.addObject("error", "Please wait before requesting a new OTP.");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", remainingSeconds);
            modelAndView.setViewName("verifyOtp");
            return modelAndView;
        }

        boolean result = hospitalService.sendOtp(email);
        if (result) {
            modelAndView.addObject("success", "A new OTP has been sent successfully!");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", 120);
        } else {
            modelAndView.addObject("error", "Failed to resend OTP. Please try again later.");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", 0);
        }
        modelAndView.setViewName("verifyOtp");
        return modelAndView;
    }

    @GetMapping("dashboard-success")
    public ModelAndView dashboardSuccess(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            modelAndView.setViewName("redirect:/admin"); // redirect if not logged in
        } else {
            modelAndView.setViewName("DashboardPageWithHistory");
        }
        return modelAndView;
    }




    @GetMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate(); // Invalidate the entire session
        modelAndView.setViewName("redirect:/admin"); // Redirect to login page
        return modelAndView;
    }




    @GetMapping("addslot")
    public ModelAndView addSlot(ModelAndView modelAndView,HttpSession httpSession){
      //  modelAndView.addObject("specializations", Specialization.values());
        modelAndView.setViewName("AddSlot");
        return modelAndView;
    }

    @GetMapping("AddSpecialization")
    public ModelAndView addSpecialization(ModelAndView modelAndView,HttpSession httpSession){
        modelAndView.setViewName("AddSpecialization");
        return modelAndView;
    }





}
