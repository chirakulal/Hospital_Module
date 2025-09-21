package com.xworkz.module.controller;

import com.xworkz.module.dto.SpecializationDTO;
import com.xworkz.module.service.SpecializationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class SpecializationController {

    @Autowired
   private SpecializationService specializationService;

    @PostMapping("specializations")
    public ModelAndView saveSpecialization(ModelAndView modelAndView, SpecializationDTO specialization, HttpSession httpSession){
        String email= (String) httpSession.getAttribute("loginEmail");
        specialization.setCreatedBy(email);
        specialization.setUpdatedBy(email);
        boolean isSaved=specializationService.saveSpecialization(specialization);
        if(isSaved){
            modelAndView.addObject("success","Specialization added successfully");
        }else{
            modelAndView.addObject("error","Failed to add specialization");
        }
        modelAndView.addObject("specialization",specialization);
        modelAndView.setViewName("AddSpecialization");
        return modelAndView;

    }
}
