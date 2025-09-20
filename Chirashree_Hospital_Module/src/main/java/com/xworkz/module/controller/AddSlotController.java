package com.xworkz.module.controller;

import com.xworkz.module.constant.Specialization;
import com.xworkz.module.service.AddSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class AddSlotController {

    public AddSlotController(){
        log.info("Created \t"+this.getClass().getSimpleName());
    }

    @Autowired
    private AddSlotService addSlotService;

    @GetMapping("schedule")
    public ModelAndView SlotDetails(ModelAndView modelAndView, @RequestParam Specialization specialization){
        List<String> doctorNames =addSlotService.getAllNames(specialization);

        List<String> timeList = addSlotService.getTime();

        log.info("{}",doctorNames);
        log.info("{}",timeList);
        modelAndView.addObject("specializations", Specialization.values());
        modelAndView.addObject("selectedSpec", specialization);

        if (doctorNames != null && !doctorNames.isEmpty()) {
            modelAndView.addObject("doctorNames", doctorNames);
            modelAndView.addObject("timeList", timeList);
            modelAndView.addObject("scheduleClicked", true);
        } else {
            modelAndView.addObject("error", "No doctors available for " + specialization);
            modelAndView.addObject("scheduleClicked", false);
        }

        modelAndView.setViewName("AddSlot");
        return modelAndView;
    }


    @PostMapping("saveSlot")
    public ModelAndView saveSlot(@RequestParam String doctorName,
                                 @RequestParam String timeSlot,
                                 ModelAndView modelAndView) {

        boolean updated = addSlotService.assignSlotToDoctor(doctorName, timeSlot);
        log.info("{}",updated);
        if (updated) {
            modelAndView.addObject("success", "Time slot assigned to " + doctorName + " successfully.");
        } else {
            modelAndView.addObject("error", "Failed to assign time slot. Please try again.");
        }

        // reload page with updated specializations
        modelAndView.addObject("specializations", Specialization.values());
        modelAndView.setViewName("AddSlot");
        return modelAndView;
    }
}
