package com.xworkz.module.controller;

import com.xworkz.module.dto.TimeSlotDTO;
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
        modelAndView.setViewName("Slot");
        return modelAndView;
    }

    @PostMapping("saveTime")
    public ModelAndView saveTimeSlots(ModelAndView modelAndView, TimeSlotDTO timeSlotDTO){

        boolean result =   slotservice.saveTimeSlot(timeSlotDTO);
        if(result){
            modelAndView.addObject("success", "TimeSlot is saved");
            modelAndView.setViewName("Slot");

        }else{
            modelAndView.addObject("success", "TimeSlot is not Saved");
            modelAndView.setViewName("Slot");
        }

        return modelAndView;
    }
}
