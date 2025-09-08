package com.xworkz.module.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.xworkz.module")
@RequestMapping("/")
@Slf4j
public class HospitalController {

    public HospitalController(){
        log.info("Controller.........");
    }


    @GetMapping("admin")
    public ModelAndView adminPage(ModelAndView modelAndView){

        modelAndView.setViewName("AdminLogin");
        return modelAndView;
    }


}
