package com.xworkz.module.service;

import com.xworkz.module.dto.DoctorDTO;
import com.xworkz.module.dto.HospitalDTO;
import com.xworkz.module.dto.TimeSlotDTO;
import com.xworkz.module.entity.DoctorEntity;
import com.xworkz.module.entity.HospitalEntity;
import com.xworkz.module.entity.TimeEntity;
import com.xworkz.module.repository.HospitalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
  private   HospitalRepo hospitalRepo;

    private HospitalDTO hospitalDTO;



    @Override
    public int emailCount(String email) {

        return Math.toIntExact(hospitalRepo.countEmail(email));
    }


   private String generatedOtp ="";
    @Override
    public boolean sendOtp(String email) {
        try {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10));
            }
            generatedOtp = sb.toString();
            LocalDateTime otpGeneratedTime = LocalDateTime.now();





            boolean result = hospitalRepo.updateOTp(email, otpGeneratedTime, generatedOtp);
            if (result) {
                sendEmailOtp(email, "OTP Sent",
                        "Dear User,\nYour OTP is: " + generatedOtp + "\nIt will expire in 2 minutes.");
                log.info("OTP generated: {} for email {}", generatedOtp, email);
                return true;
            } else {
                log.error("Failed to save OTP for email: {}", email);
                return false;
            }
        }catch (Exception e){
            log.error("Error in sendOtp service", e);
            return false;
        }
    }

    @Override
    public boolean checkOtp(String otp, String email) {

        HospitalEntity hospitalEntity = hospitalRepo.getEmail(email);
        if(hospitalEntity.getEmail()==null || hospitalEntity.getOtp()==null || hospitalEntity.getTime()==null){
            return false;
        }

      Long secondsElapsed = Duration.between(hospitalEntity.getTime(),LocalDateTime.now()).getSeconds();

        if (secondsElapsed > 120) {
            hospitalRepo.updateOTp(email,null,null);
            log.warn("OTP for {} has expired.", email);
            return false; // OTP expired
        }

        if(generatedOtp.matches(otp)){
            hospitalRepo.updateOTp(email,null,null);
            log.info("OTP verified is successfully for {}",email);
            return true;
        }
        return false;

    }

    @Override
    public int getRemainingCooldownSeconds(String email) {
        HospitalEntity hospitalEntity = hospitalRepo.getEmail(email);
        if(hospitalEntity ==null || hospitalEntity.getOtp()==null){
            return 0;
        }

        long seconds = Duration.between(hospitalEntity.getTime(),LocalDateTime.now()).getSeconds();
        return (int) Math.max(0,120-seconds);

    }

    private void sendEmailOtp(String email, String subject, String body) {
        final String username = "chirashreelk@gmail.com";
        final String password = "wvxi xvhj jfcr bgkh";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chirashreelk@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println(" OTP sent to " + email);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean saveData(DoctorDTO doctorDTO) {
        DoctorEntity doctorEntity = new DoctorEntity();


        doctorEntity.setFirstName(doctorDTO.getFirstName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setEmail(doctorDTO.getEmail());
        doctorEntity.setPhone(doctorDTO.getPhone());
        doctorEntity.setSpecialization(doctorDTO.getSpecialization());
        doctorEntity.setExperience(doctorDTO.getExperience());
        doctorEntity.setImage(doctorDTO.getImage());
        doctorEntity.setAddress(doctorDTO.getAddress());
        doctorEntity.setGender(doctorEntity.getGender());




        return hospitalRepo.saveData(doctorEntity);
    }

    @Override
    public int countLastName(String lastName) {
        return Math.toIntExact(hospitalRepo.countLastName(lastName));
    }

    @Override
    public int countPhoneNumber(String phone) {
        return Math.toIntExact(hospitalRepo.countPhoneNumber(phone));
    }

    @Override
    public boolean saveTimeSlot(TimeSlotDTO timeSlotDTO) {
        TimeEntity time = new TimeEntity();

        time.setStartTime(timeSlotDTO.getStartTime());
        time.setEndTime(timeSlotDTO.getEndTime());


        return hospitalRepo.saveTimeSlots(time);
    }

    @Override
    public List<String> getAllNames() {
        return hospitalRepo.getAllNames();
    }

    @Override
    public List<LocalTime> getTime() {
        return hospitalRepo.getTime();
    }
}
