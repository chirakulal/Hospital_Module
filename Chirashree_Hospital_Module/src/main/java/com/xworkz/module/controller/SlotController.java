package com.xworkz.module.controller;

import com.xworkz.module.dto.SlotDTO;
import com.xworkz.module.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/")
public class SlotController {

    public SlotController(){
        System.out.println("Created \t"+this.getClass().getSimpleName());
    }

    @Autowired
    private SlotService slotservice;

    @GetMapping("slot")
    public ModelAndView SlotTime(ModelAndView modelAndView, HttpSession httpSession){
        log.info("Running SlotTime in controller");
        String userName = (String) httpSession.getAttribute("userName");
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("specialization",slotservice.getAllNames());
        modelAndView.setViewName("Slot");
        return modelAndView;
    }

    @PostMapping("saveTime")
    public ModelAndView saveTimeSlots(ModelAndView modelAndView, SlotDTO slotDTO,HttpSession httpSession){
        log.info("Running saveTimeSlots in controller");

        boolean result =   slotservice.saveTimeSlot(slotDTO);
        if(result){
            modelAndView.addObject("success", "TimeSlot is saved");
            modelAndView.addObject("specialization",slotservice.getAllNames());
            modelAndView.setViewName("Slot");

        }else{
            modelAndView.addObject("success", "TimeSlot is not Saved");
            modelAndView.addObject("specialization",slotservice.getAllNames());
            modelAndView.setViewName("Slot");
        }

        return modelAndView;
    }
}
