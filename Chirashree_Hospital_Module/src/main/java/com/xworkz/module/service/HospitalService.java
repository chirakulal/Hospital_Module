package com.xworkz.module.service;


import org.springframework.stereotype.Service;


public interface HospitalService {

    int emailCount(String email);

    boolean sendOtp(String email);

     boolean checkOtp(String otp, String email);

    int getRemainingCooldownSeconds(String email);



//    int countLastName(String lastName);
//
   int countPhoneNumber(String phone);
//
//
//
//
    int countDoctorEmail(String email);

    void clearUnusedOtps();

    int countPatientEmail(String email);

    int countPatientPhoneNumber(String phone);
}
