package com.xworkz.module.controller;


import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.HospitalDTO;
import com.xworkz.module.dto.TimeSlotDTO;
import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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


    @GetMapping("doctor")
    public ModelAndView DoctorPage(ModelAndView modelAndView,HttpSession httpSession){
        modelAndView.setViewName("DoctorDetails");
        return modelAndView;
    }

    @GetMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate(); // Invalidate the entire session
        modelAndView.setViewName("redirect:/admin"); // Redirect to login page
        return modelAndView;
    }

    @PostMapping("saveDoctor")
    public ModelAndView SaveDoctor(@RequestParam("images") MultipartFile multipartFile, ModelAndView modelAndView, BindingResult bindingResult, @Valid DoctorDTO doctorDTO ) throws IOException {
        // 1. Check for validation errors from form data.
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                log.info("Validation error: {}", objectError.getDefaultMessage());
            }
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("dto", doctorDTO);
            modelAndView.setViewName("DoctorDetails");
            return modelAndView;
        }

        String originalExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String fileName = doctorDTO.getFirstName() + "_" + System.currentTimeMillis() + "." + originalExtension;
        Path uploadPath = Paths.get("D:\\chiraimage\\" + fileName);
        Files.write(uploadPath, multipartFile.getBytes());
        doctorDTO.setImage(fileName);
        log.info("Profile picture uploaded: {}", fileName);


        boolean result =   hospitalService.saveData(doctorDTO);
          if(result){
              modelAndView.addObject("success", "Registered Successfully");
              modelAndView.setViewName("DoctorDetails");
          }

        return modelAndView;
    }
    @GetMapping("slot")
    public ModelAndView SlotTime(ModelAndView modelAndView,HttpSession httpSession){
        modelAndView.setViewName("Slot");
        return modelAndView;
    }

    @PostMapping("saveTime")
    public ModelAndView saveTimeSlots(ModelAndView modelAndView, TimeSlotDTO timeSlotDTO){

        boolean result =   hospitalService.saveTimeSlot(timeSlotDTO);
        if(result){
            modelAndView.addObject("success", "Registered Successfully");
            modelAndView.setViewName("Slot");
            return modelAndView;
        }

        return modelAndView;
    }

    @GetMapping("addslot")
    public ModelAndView addSlot(ModelAndView modelAndView,HttpSession httpSession){
        modelAndView.setViewName("AddSlot");
        return modelAndView;
    }

    @GetMapping("/schedule")
  public ModelAndView SlotDetails(ModelAndView modelAndView){
        List<String> doctorNames =hospitalService.getAllNames();

        List<LocalTime> timeList = hospitalService.getTime();


        modelAndView.addObject("doctorNames", doctorNames);
        modelAndView.addObject("timeList", timeList);


        modelAndView.setViewName("AddSlot"); // your JSP
        return modelAndView;
  }


}
