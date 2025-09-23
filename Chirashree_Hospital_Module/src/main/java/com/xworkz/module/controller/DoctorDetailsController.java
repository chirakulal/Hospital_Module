package com.xworkz.module.controller;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.ImageDTO;
import com.xworkz.module.service.DoctorDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public ModelAndView SaveDoctor( ModelAndView modelAndView, @Valid DoctorDTO doctorDTO, BindingResult bindingResult ,HttpSession httpSession) throws IOException {
        log.info("Running Save Doctor");
        String email= (String) httpSession.getAttribute("loginEmail");
        doctorDTO.setCreatedBy(email);
        ImageDTO imageDTO=new ImageDTO();
        imageDTO.setCreatedBy(email);


        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("dto", doctorDTO);
            modelAndView.addObject("specializations",doctorDetailsService.getAllNames());
            modelAndView.setViewName("DoctorDetails");
            return modelAndView;
        }


        boolean result =   doctorDetailsService.saveData(doctorDTO);
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
