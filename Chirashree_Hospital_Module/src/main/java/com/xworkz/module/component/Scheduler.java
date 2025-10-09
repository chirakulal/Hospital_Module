package com.xworkz.module.component;

import com.xworkz.module.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class Scheduler {

    @Autowired
    private HospitalService hospitalService;


    @Scheduled(fixedRate = 60000)
    public void clearExpiredOtps() {
        System.out.println(">>> Running OTP cleanup at: " + java.time.LocalDateTime.now());
        hospitalService.clearUnusedOtps();
    }

}
