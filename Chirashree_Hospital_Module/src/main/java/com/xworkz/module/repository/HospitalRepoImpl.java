package com.xworkz.module.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;

@Repository
public class HospitalRepoImpl implements HospitalRepo{

    private static final Logger log = LoggerFactory.getLogger(HospitalRepoImpl.class);

    public HospitalRepoImpl(){
        log.info("Repo.................");
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Long countEmail(String email) {
        EntityManager entityManager=null;
        EntityTransaction entityTransaction = null;
        long count=0;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("countEmail");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction!=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        return count;
    }

    @Override
  public boolean updateOTp(String email, LocalDateTime time, String otp){
        log.info("Saving OTP {} for {} at time {}", otp, email, time);

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean update=false;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();


           Query query = entityManager.createNamedQuery("HospitalEntity.updateOtp");
            query.setParameter("email",email);
            query.setParameter("otp",otp);
            query.setParameter("time",time);
            int rowsUpdated = query.executeUpdate();
            log.info("Rows updated: {}", rowsUpdated);

            entityTransaction.commit();
            update = rowsUpdated>0;
        }catch (Exception e){
            if(entityTransaction!=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

     return update;
    }
}
