package com.xworkz.module.service.email;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public void sendDoctorConfirmationEmail(String doctorName, String doctorEmail) {
        final String username = "chirashreelk@gmail.com";
        final String password = "wvxi xvhj jfcr bgkh"; // Note: App password is required for security

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

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
                    InternetAddress.parse(doctorEmail)
            );
            message.setSubject("Registration Confirmation for Hospital System");
            message.setText("Dear Dr. " + doctorName + ",\n\n"
                    + "We are pleased to inform you that your profile has been successfully saved to our hospital's system.\n\n"
                    + "Your information is now available to authorized hospital staff for scheduling and administrative purposes.\n\n"
                    + "If you need to make any changes or have any questions, please contact the hospital administration.\n\n"
                    + "Best regards,\n"
                    + "The Hospital Administration Team");

            Transport.send(message);
            System.out.println("Doctor confirmation email sent to: " + doctorEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDoctorUpdateEmail(String doctorName, String doctorEmail) {
        final String username = "chirashreelk@gmail.com";
        final String password = "wvxi xvhj jfcr bgkh"; // Note: Use your App Password

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

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
                    InternetAddress.parse(doctorEmail)
            );
            message.setSubject("Profile Update Confirmation for Hospital System");
            message.setText("Dear Dr. " + doctorName + ",\n\n"
                    + "This email is to confirm that your profile details in our hospital's system have been successfully updated.\n\n"
                    + "The changes you requested are now reflected in our records.\n\n"
                    + "If you did not make these changes, please contact the hospital administration immediately.\n\n"
                    + "Best regards,\n"
                    + "The Hospital Administration Team");

            Transport.send(message);
            System.out.println("Doctor update email sent to: " + doctorEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}