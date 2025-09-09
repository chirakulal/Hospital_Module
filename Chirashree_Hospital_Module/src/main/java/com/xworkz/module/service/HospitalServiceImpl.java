package com.xworkz.module.service;

import com.xworkz.module.repository.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
  private   HospitalRepo hospitalRepo;
    @Override
    public int emailCount(String email) {

        return Math.toIntExact(hospitalRepo.countEmail(email));
    }
}
