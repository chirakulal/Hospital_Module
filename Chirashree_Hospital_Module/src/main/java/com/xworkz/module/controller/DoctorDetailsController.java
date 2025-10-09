package com.xworkz.module.controller;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.service.DoctorDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/")
public class DoctorDetailsController {

    public DoctorDetailsController(){
        log.info("Doctor Controller.........");
    }

    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @GetMapping("doctor")
    public ModelAndView DoctorPage(ModelAndView modelAndView, HttpSession httpSession){

        log.info("Running Doctor Page");
        modelAndView.addObject("specializations",doctorDetailsService.getAllNames());
        modelAndView.setViewName("DoctorDetails");
        return modelAndView;
    }


    @PostMapping("saveDoctor")
    public ModelAndView SaveDoctor(@RequestParam("image")MultipartFile file, ModelAndView modelAndView, @Valid DoctorDTO doctorDTO, BindingResult bindingResult ,HttpSession httpSession) throws IOException {
        log.info("Running Save Doctor");
        log.info("Received file: " + file.getOriginalFilename());
        log.info("File size: " + file.getSize());
        log.info("Doctor DTO: " + doctorDTO);



        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("dto", doctorDTO);
            modelAndView.addObject("specializations",doctorDetailsService.getAllNames());
            modelAndView.setViewName("DoctorDetails");
            return modelAndView;
        }


        boolean result =   doctorDetailsService.saveData(file,doctorDTO);
        if(result){
            modelAndView.addObject("success", "Registered Successfully");
            modelAndView.addObject("specializations",doctorDetailsService.getAllNames());
            modelAndView.setViewName("DoctorDetails");
        }else{
            modelAndView.addObject("error","Registration is Unsuccessfully");
            modelAndView.addObject("specializations",doctorDetailsService.getAllNames());
            modelAndView.addObject("dto",doctorDTO);
            modelAndView.setViewName("DoctorDetails");
        }

        return modelAndView;
    }
}
