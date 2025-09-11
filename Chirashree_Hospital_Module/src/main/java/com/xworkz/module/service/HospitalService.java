package com.xworkz.module.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public interface HospitalService {

    int emailCount(String email);

    boolean sendOtp(String email);

     boolean checkOtp(String otp, LocalDateTime sentTime, String email);
}
