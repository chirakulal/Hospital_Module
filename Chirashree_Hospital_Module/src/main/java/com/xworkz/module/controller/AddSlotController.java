package com.xworkz.module.controller;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.SpecializationDTO;
import com.xworkz.module.service.AddSlotService;
import com.xworkz.module.service.SpecializationService;
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

    @Autowired
    private SpecializationService specializationService;

    @GetMapping("schedule")
    public ModelAndView SlotDetails(ModelAndView modelAndView, @RequestParam String specializationName){
        List<String> doctorNames =addSlotService.getAllNames(specializationName);

        List<String> timeList = addSlotService.getTime(specializationName);

        log.info("{}",doctorNames);
        log.info("{}",timeList);
        modelAndView.addObject("specializations",specializationService.getAllNames() );
        modelAndView.addObject("selectedSpec", specializationName);

        if (doctorNames != null && !doctorNames.isEmpty()) {
            modelAndView.addObject("doctorNames", doctorNames);
            modelAndView.addObject("timeList", timeList);
            modelAndView.addObject("scheduleClicked", true);
        } else {
            modelAndView.addObject("error", "No doctors available for " + specializationName);
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

        modelAndView.addObject("specializations", specializationService.getAllNames());
        modelAndView.addObject("doctorNames", addSlotService.getAllNames(doctorName));
        modelAndView.addObject("timeList", addSlotService.getTime(timeSlot));

        modelAndView.setViewName("AddSlot");
        return modelAndView;
    }
}
