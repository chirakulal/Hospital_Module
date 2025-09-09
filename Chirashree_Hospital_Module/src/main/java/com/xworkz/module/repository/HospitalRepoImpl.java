package com.xworkz.module.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
            Query query = entityManager.createQuery("countEmail");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction==null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        return count;
    }
}
