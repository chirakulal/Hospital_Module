package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
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

    private String uploadDir = "D:\\chiraimage\\HospitalProject\\DoctorProfile";

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


    @Override
    public List<String> getAllNames(String specialization) {
        EntityManager entityManager =null;
        EntityTransaction entityTransaction =null;
        List<String> list = null;

        try{
            entityManager =entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Query query =entityManager.createNamedQuery("DoctorEntity.getNamesBySpecialization");
            query.setParameter("specializationName",specialization);
           list = query.getResultList();

            return list;
        }catch (Exception e){
            if(entityTransaction !=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
       return list;
    }

    @Override
    public List<String> getTime(String specialization) {
        EntityManager entityManager =null;
        EntityTransaction entityTransaction =null;
        List<String> list =null ;

        try{
            entityManager =entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Query query =entityManager.createNamedQuery("SlotEntity.getTimeBySpecialization");
            query.setParameter("specializationName",specialization);
           list =  query.getResultList();
           log.info("{}",list);
            return list;
        }catch (Exception e){
            if(entityTransaction !=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return list;
    }

    @Override
    public boolean assignSlotToDoctor(String doctorName, String timeSlot) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean update = false;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
           Query query = entityManager.createNamedQuery("DoctorEntity.updateSlotByName");
            query.setParameter("doctorName",doctorName);
            query.setParameter("timeSlot",timeSlot);
            int rowsUpdated = query.executeUpdate();
            log.info("Rows updated: {}", rowsUpdated);

            entityTransaction.commit();
            update = rowsUpdated>0;


        }catch (Exception e){
            if(entityTransaction!=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
        }finally {
            entityManager.close();
        }

//
      return update;
    }
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

    @Override
    public List<DoctorEntity> getAllDoctors() {
        EntityManager entityManager =null;
        EntityTransaction entityTransaction =null;
        List<DoctorEntity> doctorEntity = null;

        try{
            entityManager =entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Query query =entityManager.createNamedQuery("DoctorEntity.getAllDoctorDetails");
             doctorEntity= query.getResultList();
            log.info("{}",doctorEntity);
            entityTransaction.commit();
            return doctorEntity;
        }catch (Exception e){
            if(entityTransaction !=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return doctorEntity;
    }

    @Override
    public DoctorEntity getAllDoctorsByEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        DoctorEntity doctor = null;
try{
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction =entityManager.getTransaction();
        entityTransaction.begin();
       Query query= entityManager.createNamedQuery("DoctorEntity.getAllDoctorDetailsByEmail");
        query.setParameter("email",email);
        doctor =(DoctorEntity) query.getSingleResult();
        entityTransaction.commit();
        return doctor;
    }catch (Exception e){
    if(entityTransaction!=null &&entityTransaction.isActive()){
        entityTransaction.rollback();
    }
    e.printStackTrace();
    }finally {
    entityManager.close();
    }
return null;
    }

    @Override
    public boolean updateDoctor(MultipartFile file,DoctorEntity doctorEntity) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        DoctorEntity doctorEntity1 = new DoctorEntity();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            doctorEntity1 = getAllDoctorsByEmail(doctorEntity.getEmail());
            log.info("Existing DoctorEntity: {}", doctorEntity1);
            log.info("Updating with DoctorEntity: {}", doctorEntity);
            if (doctorEntity1 != null) {
                doctorEntity1.setFirstName(doctorEntity.getFirstName());
                doctorEntity1.setLastName(doctorEntity.getLastName());
                doctorEntity1.setPhone(doctorEntity.getPhone());
                doctorEntity1.setSpecializationName(doctorEntity.getSpecializationName());
                doctorEntity1.setExperience(doctorEntity.getExperience());
                doctorEntity1.setAddress(doctorEntity.getAddress());
                doctorEntity1.setGender(doctorEntity.getGender());
                doctorEntity1.setUpdatedBy(doctorEntity.getUpdatedBy());
                doctorEntity1.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                ImageEntity imageEntity = new ImageEntity();
                // --- Handle Profile Image ---
                if (file != null && !file.isEmpty()) {
                    // Save the uploaded file to the server
                    String originalExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                    String fileName = doctorEntity.getFirstName() + "_" + System.currentTimeMillis() + "." + originalExtension;
                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath);

                    // Create new ImageEntity

                    imageEntity.setOriginalImageName(file.getOriginalFilename());
                    imageEntity.setFileType(file.getContentType());
                    imageEntity.setFileSize(file.getSize());
                    imageEntity.setFilePath(filePath.toString());
                    imageEntity.setSavedName(fileName);
                    imageEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
                    imageEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    imageEntity.setUpdatedBy(doctorEntity.getUpdatedBy());

                    // Associate with doctor
                    doctorEntity1.setProfilePicture(imageEntity);
                }else if (imageEntity.getSavedName() != null) {
                    // Keep old image if no new upload
                    doctorEntity1.setProfilePicture(doctorEntity1.getProfilePicture());
                }
                if (doctorEntity.getDegree() != null) {
                    doctorEntity1.setDegree(doctorEntity.getDegree());
                }
                entityManager.merge(doctorEntity1);
                entityTransaction.commit();
                return true;


            }
        }catch (Exception e) {
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
    public DoctorEntity getAllDoctorsById(int id) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        DoctorEntity doctor = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction =entityManager.getTransaction();
            entityTransaction.begin();
            Query query= entityManager.createNamedQuery("DoctorEntity.getAllDoctorDetailsById");
            query.setParameter("id",id);
            doctor =(DoctorEntity) query.getSingleResult();
            entityTransaction.commit();
            return doctor;
        }catch (Exception e){
            if(entityTransaction!=null &&entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public boolean DeleteDoctorById(int id) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean deleted = false;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, id);
            if (doctorEntity != null) {
                entityManager.remove(doctorEntity);
                entityTransaction.commit();
                deleted = true;
            } else {
                log.info("Doctor with id {} not found", id);
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return deleted;
    }

    @Override
    public String getTimeSlotByEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        String timeSlot=null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction =entityManager.getTransaction();
            entityTransaction.begin();
            Query query= entityManager.createNamedQuery("DoctorEntity.getTimeSlotByEmail");
            query.setParameter("email",email);
            timeSlot =(String) query.getSingleResult();
            log.info("Time Slot: {}", timeSlot);
            entityTransaction.commit();
            return timeSlot;
        }catch (Exception e){
            if(entityTransaction!=null &&entityTransaction.isActive()){
                entityTransaction.rollback();
            }   e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return timeSlot;
    }
}
