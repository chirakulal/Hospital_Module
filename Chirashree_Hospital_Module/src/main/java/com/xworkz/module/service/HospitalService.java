package com.xworkz.module.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public interface HospitalService {

    int emailCount(String email);

    void sendOtp(String email);
}
