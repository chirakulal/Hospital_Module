package com.xworkz.module.repository;

import com.xworkz.module.entity.*;
import jdk.nashorn.internal.runtime.Specialization;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
            entityTransaction.commit();
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
    public boolean isTimeSlotExist(String specializationName, LocalTime startTime, LocalTime endTime) {
        EntityManager entityManager = null;
        long count = 0;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("checkTimeSlotExist");
            query.setParameter("specName", specializationName);
            query.setParameter("start", startTime);
            query.setParameter("end", endTime);

            count = (long) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return count > 0; // ✅ Return true if slot exists
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
           entityTransaction.commit();
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
    public List<String> getAvailableTimeForDoctor(String specialization, int doctorId) {
        if (entityManagerFactory == null) {
            log.error("EntityManagerFactory is null!");
            return new ArrayList<>();
        }

        EntityManager entityManager = null;
        List<String> list = new ArrayList<>();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNamedQuery("SlotEntity.getAvailableTimeForDoctor");
            query.setParameter("spec", specialization);
            query.setParameter("docId", doctorId);

            list = query.getResultList();
            log.info("Available slots: {}", list);

        } catch (Exception e) {
            log.error("Error fetching available time slots", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

        return list;
    }


    @Override
    public DoctorEntity findByFullName(String doctorName) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        DoctorEntity doctor = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction =entityManager.getTransaction();
            entityTransaction.begin();
            Query query= entityManager.createNamedQuery("DoctorEntity.findByFullName");
            query.setParameter("doctorName",doctorName);
            doctor =(DoctorEntity) query.getSingleResult();
            entityTransaction.commit();
            return doctor;
        }catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        }finally {
            entityManager.close();
        }


        return null;
    }

    @Override
    public boolean assignSlotToDoctor(TimeSlotEntity timeSlotEntity) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean update = false;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(timeSlotEntity);
            entityTransaction.commit();
            update = true;
            return update;


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
        List<DoctorEntity> doctorEntity = new ArrayList<>();

        try{
            entityManager =entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Query query =entityManager.createNamedQuery("DoctorEntity.getAllDoctorDetailsWithProjection");
             List<Object[]> result= query.getResultList();

            for (Object[] row : result) {
                DoctorEntity doctor = (DoctorEntity) row[0];
                doctorEntity.add(doctor);
            }
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
    public boolean DeleteDoctorByEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        boolean deleted = false;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
           Query query = entityManager.createNamedQuery("DoctorEntity.DeleteByEmail");
            query.setParameter("email", email);
          int  rowsDeleted = query.executeUpdate();
            log.info("Rows deleted: {}", rowsDeleted);
            entityTransaction.commit();
            if (rowsDeleted > 0) {
                deleted = true;
                return deleted;
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
    public List<String> getTimeSlotByEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        List<String> timeSlot=null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction =entityManager.getTransaction();
            entityTransaction.begin();
            Query query= entityManager.createNamedQuery("DoctorEntity.getTimeSlotByEmail");
            query.setParameter("email",email);
            timeSlot = query.getResultList();
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

    @Override
    public boolean savePatientData(PatientEntity patientEntity) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(patientEntity);
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
    public TimeSlotEntity getTImeSlotIdByTime(String time) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        TimeSlotEntity timeSlot = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("TimeSlotEntity.getTimeSlotIdByTime");
            query.setParameter("timeSlot", time);
            timeSlot = (TimeSlotEntity) query.getSingleResult();
            log.info("Time Slot Entity: {}", timeSlot);
            entityTransaction.commit();
            return timeSlot;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        return timeSlot;
    }

    @Override
    public PatientEntity getPatientByEmail(String email) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        PatientEntity patient = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction =entityManager.getTransaction();
            entityTransaction.begin();
            Query query= entityManager.createNamedQuery("PatientEntity.getByEmail");
            query.setParameter("email",email);
            try {
                // Line 698: This is where getSingleResult() throws the exception\
                patient = (PatientEntity) query.getSingleResult();
                return patient;
            } catch (NoResultException e) {
                // If no entity is found, return null instead of throwing an exception
                return null;
            } catch (NonUniqueResultException e) {
                // Good practice: this happens if the query returns > 1 result (shouldn't happen for a unique field like ID/Email)
                log.error("Multiple results found for unique field query: {}", email);
                throw e; // Or handle as needed
            }
            //entityTransaction.commit();
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

}
