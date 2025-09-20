package com.xworkz.module.controller;

import com.xworkz.module.constant.Specialization;
import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.service.DoctorDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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


    @PostMapping("saveDoctor")
    public ModelAndView SaveDoctor(@RequestParam("images") MultipartFile multipartFile, ModelAndView modelAndView, @Valid DoctorDTO doctorDTO, BindingResult bindingResult ) throws IOException {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.addObject("dto", doctorDTO);
            modelAndView.setViewName("DoctorDetails");
            return modelAndView;
        }

        String originalExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String fileName = doctorDTO.getFirstName() + "_" + System.currentTimeMillis() + "." + originalExtension;
        Path uploadPath = Paths.get("D:\\chiraimage\\HospitalProject\\DoctorProfile" + fileName);
        Files.write(uploadPath, multipartFile.getBytes());
        doctorDTO.setImage(fileName);
        log.info("Profile picture uploaded: {}", fileName);


        boolean result =   doctorDetailsService.saveData(doctorDTO);
        if(result){
            modelAndView.addObject("success", "Registered Successfully");
            modelAndView.addObject("specializations", Specialization.values());
            modelAndView.setViewName("DoctorDetails");
        }else{
            modelAndView.addObject("error","Registration is Unsuccessfully");
            modelAndView.addObject("specializations", Specialization.values());
            modelAndView.addObject("dto",doctorDTO);
            modelAndView.setViewName("DoctorDetails");
        }

        return modelAndView;
    }
}
