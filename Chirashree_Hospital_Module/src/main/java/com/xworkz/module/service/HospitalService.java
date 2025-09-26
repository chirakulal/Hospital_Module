package com.xworkz.module.service;


import org.springframework.stereotype.Service;


public interface HospitalService {

    int emailCount(String email);

    boolean sendOtp(String email);

     boolean checkOtp(String otp, String email);

    int getRemainingCooldownSeconds(String email);



//    int countLastName(String lastName);
//
   int countPhoneNumber(long phone);
//
//
//
//
    int countDoctorEmail(String email);
}
