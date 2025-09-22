package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class HospitalRepoImpl implements HospitalRepo {

    private static final Logger log = LoggerFactory.getLogger(HospitalRepoImpl.class);

    public HospitalRepoImpl() {
        log.info("Repo.................");
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Long countEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        long count = 0;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("countEmail");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return count;
    }

    @Override
    public boolean updateOTp(String email, LocalDateTime time, String otp) {
        log.info("Saving OTP {} for {} at time {}", otp, email, time);

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean update = false;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();


            Query query = entityManager.createNamedQuery("HospitalEntity.updateOtp");
            query.setParameter("email", email);
            query.setParameter("otp", otp);
            query.setParameter("time", time);
            int rowsUpdated = query.executeUpdate();
            log.info("Rows updated: {}", rowsUpdated);

            entityTransaction.commit();
            update = rowsUpdated > 0;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return update;
    }

    @Override
    public HospitalEntity getEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        HospitalEntity hospitalEntity;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("getByEmail");
            query.setParameter("email", email);
            hospitalEntity = (HospitalEntity) query.getSingleResult();

            entityTransaction.commit();

            return hospitalEntity;

        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }


        return null;
    }

    @Override
    public boolean saveSpecializationData(SpecializationEntity specializationEntity) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(specializationEntity);
            entityTransaction.commit();

            return true;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    return false;
}

@Override
    public List<String> getAllSpecializations() {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        List<String> list = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("getAllSpecializations");
            list = query.getResultList();

            return list;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return list;
    }

    @Override
    public boolean saveData(DoctorEntity doctorEntity) {

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(doctorEntity);
            entityTransaction.commit();

            return true;
        }catch (Exception e){
            if(entityTransaction !=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }



        return false;
    }

//
//    public Long countLastName(String lastName) {
//        EntityManager entityManager=null;
//        EntityTransaction entityTransaction = null;
//        long count=0;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityTransaction = entityManager.getTransaction();
//
//            entityTransaction.begin();
//            Query query = entityManager.createNamedQuery("countLastname");
//            query.setParameter("lastName", lastName);
//            count = (long) query.getSingleResult();
//            entityTransaction.commit();
//        }catch (Exception e){
//            if(entityTransaction!=null && entityTransaction.isActive()){
//                entityTransaction.rollback();
//            }
//            e.printStackTrace();
//        }finally {
//            entityManager.close();
//        }
//
//        return count;
//    }
//
    @Override
    public Long countPhoneNumber(long phone) {
        EntityManager entityManager=null;
        EntityTransaction entityTransaction = null;
        long count=0;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("countPhoneNumber");
            query.setParameter("phone", phone);
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
    public boolean saveTimeSlots(SlotEntity slotEntity) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(slotEntity);
            entityTransaction.commit();

            return true;
        }catch (Exception e){
            if(entityTransaction !=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }


        return false;
    }

//    @Override
//    public List<String> getAllNames(Specialization specialization) {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public List<String> getAllNames(Specialization specialization) {
//        EntityManager entityManager =null;
//        EntityTransaction entityTransaction =null;
//        List<String> list = null;
//
//        try{
//            entityManager =entityManagerFactory.createEntityManager();
//            entityTransaction=entityManager.getTransaction();
//            entityTransaction.begin();
//            Query query =entityManager.createNamedQuery("DoctorEntity.getNamesBySpecialization");
//            query.setParameter("specialization",specialization);
//           list = query.getResultList();
//
//            return list;
//        }catch (Exception e){
//            if(entityTransaction !=null && entityTransaction.isActive()){
//                entityTransaction.rollback();
//            }
//            e.printStackTrace();
//        }finally {
//            entityManager.close();
//        }
//       return list;
//    }

//    @Override
//    public List<String> getTime() {
//        EntityManager entityManager =null;
//        EntityTransaction entityTransaction =null;
//        List<String> list =null ;
//
//        try{
//            entityManager =entityManagerFactory.createEntityManager();
//            entityTransaction=entityManager.getTransaction();
//            entityTransaction.begin();
//            Query query =entityManager.createNamedQuery("TimeEntity.getTime");
//
//           list =  query.getResultList();
//           log.info("{}",list);
//            return list;
//        }catch (Exception e){
//            if(entityTransaction !=null && entityTransaction.isActive()){
//                entityTransaction.rollback();
//            }
//            e.printStackTrace();
//        }finally {
//            entityManager.close();
//        }
//        return list;
//    }
//
//    @Override
//    public boolean assignSlotToDoctor(String doctorName, String timeSlot) {
//        EntityManager entityManager = null;
//        EntityTransaction entityTransaction = null;
//        boolean update = false;
//
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityTransaction = entityManager.getTransaction();
//
//            entityTransaction.begin();
//           Query query = entityManager.createNamedQuery("DoctorEntity.updateSlotByName");
//            query.setParameter("doctorName",doctorName);
//            query.setParameter("timeSlot",timeSlot);
//            int rowsUpdated = query.executeUpdate();
//            log.info("Rows updated: {}", rowsUpdated);
//
//            entityTransaction.commit();
//            update = rowsUpdated>0;
//
//
//        }catch (Exception e){
//            if(entityTransaction!=null && entityTransaction.isActive()){
//                entityTransaction.rollback();
//            }
//        }finally {
//            entityManager.close();
//        }
//
//
//        return update;
//    }
//
    @Override
    public Long countDoctorEmail(String email) {
        EntityManager entityManager=null;
        EntityTransaction entityTransaction = null;
        long count=0;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("DoctorEntity.getByEmail");
            query.setParameter("email", email);
            count = (long) query.getSingleResult();
            log.info("{}",count);
            entityTransaction.commit();
            return count;
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
}
