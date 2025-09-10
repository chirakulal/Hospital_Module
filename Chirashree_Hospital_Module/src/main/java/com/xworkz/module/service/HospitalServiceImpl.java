package com.xworkz.module.service;

import com.xworkz.module.repository.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
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


@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
  private   HospitalRepo hospitalRepo;
    @Override
    public int emailCount(String email) {

        return Math.toIntExact(hospitalRepo.countEmail(email));
    }

    @Override
    public void sendOtp(String email) {

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<6;i++){
            stringBuilder.append(random.nextInt(10));
        }
        String generatedOtp = stringBuilder.toString();

        hospitalRepo.updateOTp(email, LocalDateTime.now(),generatedOtp);

        sendEmailOtp(email,"OTP sent","Dear User,\nYour OTP is: "+generatedOtp + "\n It will expire in 5 minutes");
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
