package com.xworkz.module.service.email;

import com.xworkz.module.entity.PatientEntity;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
public class EmailService {

    private final String username = "chirashreelk@gmail.com";
    private final String password = "wvxi xvhj jfcr bgkh"; // App-specific password

    private Session getEmailSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendDoctorConfirmationEmail(String doctorName, String doctorEmail) {
        try {
            Session session = getEmailSession();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(doctorEmail));
            message.setSubject("Registration Confirmation for Hospital System");

            message.setText("Dear Dr. " + doctorName + ",\n\n"
                    + "We are pleased to inform you that your profile has been successfully saved to our hospital's system.\n\n"
                    + "Your information is now available to authorized hospital staff for scheduling and administrative purposes.\n\n"
                    + "Best regards,\n"
                    + "The Hospital Administration Team");

            Transport.send(message);
            System.out.println("Doctor confirmation email sent to: " + doctorEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDoctorUpdateEmail(String doctorName, String doctorEmail) {
        try {
            Session session = getEmailSession();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(doctorEmail));
            message.setSubject("Profile Update Confirmation for Hospital System");

            message.setText("Dear Dr. " + doctorName + ",\n\n"
                    + "This email is to confirm that your profile details in our hospital's system have been successfully updated.\n\n"
                    + "If you did not make these changes, please contact the hospital administration immediately.\n\n"
                    + "Best regards,\n"
                    + "The Hospital Administration Team");

            Transport.send(message);
            System.out.println("Doctor update email sent to: " + doctorEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ New method for sending patient appointment details
    public void sendPatientAppointmentEmail(PatientEntity patient) {
        try {
            Session session = getEmailSession();

            // Format appointment date
            String formattedDate = (patient.getAppointmentDate() != null)
                    ? patient.getAppointmentDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    : "N/A";

            // Combine doctor's first and last name
            String doctorName = (patient.getDoctor() != null)
                    ? patient.getDoctor().getFirstName() + " " + patient.getDoctor().getLastName()
                    : "N/A";

            // Get specialization name
            String specialization = (patient.getSpecializationName() != null)
                    ? patient.getSpecializationName()
                    : "N/A";

            // Combine slot start and end time
            String timeSlot = (patient.getSlot() != null)
                    ? patient.getSlot().getTimeSlot()
                    : "N/A";

            // Create email body
            String emailBody = "Dear " + patient.getFirstName() + " " + patient.getLastName() + ",\n\n"
                    + "Your appointment has been successfully booked!\n\n"
                    + "Here are your appointment details:\n"
                    + "------------------------------------\n"
                    + "Registration ID: " + patient.getRegistrationId() + "\n"
                    + "Doctor: Dr. " + doctorName + "\n"
                    + "Specialization: " + specialization + "\n"
                    + "Date: " + formattedDate + "\n"
                    + "Time Slot: " + timeSlot + "\n"
                    + "Fees: ₹" + (patient.getFees() != null ? patient.getFees() : "N/A") + "\n"
                    + "------------------------------------\n\n"
                    + "Please arrive 10 minutes before your scheduled time.\n"
                    + "If you wish to cancel or reschedule, contact the hospital administration.\n\n"
                    + "Thank you for choosing our hospital.\n\n"
                    + "Best regards,\n"
                    + "The Hospital Administration Team";

            // Prepare message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(patient.getEmail()));
            message.setSubject("Appointment Confirmation - " + formattedDate);
            message.setText(emailBody);

            // Send email
            Transport.send(message);
            System.out.println("✅ Patient appointment email sent to: " + patient.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
