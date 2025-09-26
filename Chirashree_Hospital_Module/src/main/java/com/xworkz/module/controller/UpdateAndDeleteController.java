package com.xworkz.module.controller;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.UpdateDTO;
import com.xworkz.module.service.DoctorDetailsService;
import com.xworkz.module.service.UpdateDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class UpdateAndDeleteController {

    public UpdateAndDeleteController() {
        log.info("Created \t" + this.getClass().getSimpleName());
    }

    @Autowired
    private UpdateDetailsService updateDetailsService;


    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @GetMapping("/download")
    public void getImage(HttpServletResponse response, @RequestParam String fileName) throws IOException {
        response.setContentType("image/jps");
        File file = new File("D:\\chiraimage\\HospitalProject\\DoctorProfile\\" + fileName);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        response.flushBuffer();
    }


    @GetMapping("updateDetails")
    public ModelAndView updateDetails(ModelAndView modelAndView) {
        log.info("Running updateDetails in UpdateAndDeleteController");
        List<DoctorDTO> doctorDTOS = updateDetailsService.getAllDoctors();
        for (DoctorDTO dto : doctorDTOS) {
            log.info("DoctorDTO: " + dto);
        }
        modelAndView.addObject("doctorDTOS", doctorDTOS);
        modelAndView.setViewName("UpdateDetails");
        return modelAndView;
    }


    @GetMapping("updateDoctor")
    public ModelAndView updateDoctor(@RequestParam String email, ModelAndView modelAndView) {
        log.info("Running updateDoctor in UpdateAndDeleteController with email: " + email);
        DoctorDTO doctorDTOS = updateDetailsService.getDoctorByEmail(email);
        List<String> specializations = doctorDetailsService.getAllNames();
        log.info("Specializations: " + specializations);
        if (doctorDTOS != null) {
            modelAndView.addObject("doctor", doctorDTOS);
            modelAndView.addObject("specializations", specializations);
            modelAndView.setViewName("UpdateDoctorDetails");
            log.info("Found DoctorDTO: " + doctorDTOS);
        } else {
            modelAndView.addObject("error", "Doctor not found with ID: " + email);
            modelAndView.setViewName("UpdateDetails");
            log.warn("No DoctorDTO found with email: " + email);
        }

        return modelAndView;
    }

    @PostMapping("saveUpdate")
    public ModelAndView saveUpdate(@Valid UpdateDTO updateDTO, BindingResult bindingResult, ModelAndView modelAndView,@RequestParam("image") MultipartFile file, HttpSession httpSession) {

        log.info("Running saveUpdate in UpdateAndDeleteController");
        log.info("Received DoctorDTO: " + updateDTO);
        String email= (String) httpSession.getAttribute("loginEmail");
        updateDTO.setUpdatedBy(email);
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found: " + bindingResult.getAllErrors());
            modelAndView.addObject("error", "Validation errors occurred. Please correct the form.");
            modelAndView.setViewName("UpdateDoctorDetails");
            return modelAndView;
        }

        boolean isUpdated = updateDetailsService.UpdateDoctor(file,updateDTO);
        if (isUpdated) {
            modelAndView.addObject("success", "Doctor details updated successfully.");
            log.info("Doctor details updated successfully for email: " + updateDTO.getEmail());
        } else {
            modelAndView.addObject("error", "Failed to update doctor details. Please try again.");
            log.error("Failed to update doctor details for email: " + updateDTO.getEmail());
        }
        List<DoctorDTO> doctorDTOS = updateDetailsService.getAllDoctors();
        modelAndView.addObject("doctorDTOS", doctorDTOS);
        modelAndView.setViewName("UpdateDetails");
        return modelAndView;

    }

    @GetMapping("deleteDoctor")
    public ModelAndView deleteDoctor(@RequestParam String email, ModelAndView modelAndView) {
        log.info("Running deleteDoctor in UpdateAndDeleteController with email: " + email);
        boolean isDeleted = updateDetailsService.DeleteDoctorByEmail(email);
        if (isDeleted) {
            modelAndView.addObject("success", "Doctor deleted successfully.");
        } else {
            modelAndView.addObject("error", "Failed to delete doctor. Please try again.");
        }
        List<DoctorDTO> doctorDTOS = updateDetailsService.getAllDoctors();
        modelAndView.addObject("doctorDTOS", doctorDTOS);
        modelAndView.setViewName("UpdateDetails");
        return modelAndView;
    }
}

