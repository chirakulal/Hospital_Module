package com.xworkz.module.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
public class SchedulerRepoImpl implements SchedulerRepo {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void clearExpiredOtps(LocalDateTime expiryTime) {
        String query = "UPDATE HospitalEntity h SET h.otp = null, h.time = null WHERE h.time < :expiryTime";
        entityManager.createQuery(query)
                .setParameter("expiryTime", expiryTime)
                .executeUpdate();
    }
}
