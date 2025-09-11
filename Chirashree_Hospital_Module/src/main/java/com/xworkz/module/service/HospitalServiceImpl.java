package com.xworkz.module.service;

import com.xworkz.module.entity.HospitalEntity;
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
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
  private   HospitalRepo hospitalRepo;

    @Override
    public int emailCount(String email) {

        return Math.toIntExact(hospitalRepo.countEmail(email));
    }

    private String generatedOtp = "";
    private LocalDateTime otpGeneratedTime;

    @Override
    public boolean sendOtp(String email) {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        generatedOtp = sb.toString();
        otpGeneratedTime = LocalDateTime.now();


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedOtp = encoder.encode(generatedOtp);

        boolean result = hospitalRepo.updateOTp(email, otpGeneratedTime, encodedOtp);
        if (result) {
            sendEmailOtp(email, "OTP Sent",
                    "Dear User,\nYour OTP is: " + generatedOtp + "\nIt will expire in 2 minutes.");
            log.info("OTP generated: {} for email {}", generatedOtp, email);
            return true;
        } else {
            log.error("Failed to save OTP for email: {}", email);
            return false;
        }
    }

    @Override
    public boolean checkOtp(String otp, LocalDateTime sentTime, String email) {

        if (sentTime.plusMinutes(2).isBefore(LocalDateTime.now())) {
            log.warn("OTP expired.");
            return false;
        }


        HospitalEntity hospitalEntity = hospitalRepo.getEmail(email);
        String storedEncodedOtp = hospitalEntity.getOtp();

        if (storedEncodedOtp == null) {
            log.error("No OTP found for email {}", email);
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(otp, storedEncodedOtp)) {
            log.info("OTP matched successfully for email {}", email);
            return true;
        } else {
            log.info("OTP did not match for email {}", email);
            return false;
        }
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
}
