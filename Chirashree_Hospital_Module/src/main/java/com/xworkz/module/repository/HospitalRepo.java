package com.xworkz.module.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


public interface HospitalRepo {

    Long countEmail(String email);

    boolean updateOTp(String email, LocalDateTime time, String otp);
}
