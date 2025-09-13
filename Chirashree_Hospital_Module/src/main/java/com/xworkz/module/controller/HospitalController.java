package com.xworkz.module.controller;


import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.HospitalDTO;
import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
                                @RequestParam String email) {

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
                                  @RequestParam String email) {


        boolean check = hospitalService.checkOtp(otp,  email);
        if (!check) {
         int remaining =   hospitalService.getRemainingCooldownSeconds(email);
            modelAndView.addObject("error", "Invalid or expired OTP. Please try again!");
            modelAndView.addObject("email", email);
            modelAndView.addObject("remainingSeconds", remaining);
            modelAndView.setViewName("verifyOtp");
        } else {
            modelAndView.addObject("success", "OTP verified successfully!");
            modelAndView.setViewName("Home");
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

    @GetMapping("doctors")
    public ModelAndView DoctorPage(ModelAndView modelAndView){

        modelAndView.setViewName("DoctorDetails");
        return modelAndView;
    }

    @PostMapping("saveDoctor")
    public ModelAndView SaveDoctor(ModelAndView modelAndView, BindingResult bindingResult, DoctorDTO doctorDTO, @RequestParam("profilePicture") MultipartFile multipartFile ) throws IOException {

        byte[] bytes=multipartFile .getBytes();

        Path path= Paths.get("D:\\chiraimage\\HospitalProject\\DoctorProfile\\"+doctorDTO.getFirstName()+System.currentTimeMillis()+".jpg");

        Files.write(path,bytes);
        String image=path.getFileName().toString();
        doctorDTO.setImage(path.toString());

        System.out.println("image name"+image);
        log.info("Customer DTO: {}", doctorDTO.getImage());



        if(bindingResult.hasErrors()){
          List<ObjectError> objectErrorList =  bindingResult.getAllErrors();
          for(ObjectError objectError:objectErrorList){
              log.info("{}",objectError);
              modelAndView.addObject("error",objectError.getDefaultMessage());
              modelAndView.setViewName("DoctorDetails");
              modelAndView.addObject("dto",doctorDTO);
              return modelAndView;

          }



         boolean result =   hospitalService.saveData(doctorDTO);
          if(result){
              modelAndView.addObject("success", "Registered Successfully");
              modelAndView.setViewName("DoctorDetails");
          }
        }




        return modelAndView;
    }

}
