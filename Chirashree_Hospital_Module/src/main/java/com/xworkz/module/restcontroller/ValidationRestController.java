package com.xworkz.module.restcontroller;

import com.xworkz.module.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class ValidationRestController {
    public ValidationRestController(){
        log.info("rest controller.........");
    }
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("checkEmail/{email}")
    public String checkEmailCount(@PathVariable String email){
        log.info(email);
        int count = hospitalService.emailCount(email);
        log.info(String.valueOf(count));
        if(count==0) return "";
        else return "Email already exist";

    }

}
