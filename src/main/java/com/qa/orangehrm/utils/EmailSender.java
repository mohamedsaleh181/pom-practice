package com.qa.orangehrm.utils;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        // Sender's email credentials
        String username = System.getenv("GMAIL_EMAIL"); // Your Gmail address
        String password = System.getenv("GMAIL_PASSWORD"); // Your Gmail app password
        String allureReportLink = System.getenv("ALLURE_REPORT_LINK"); // Allure report link
        String recipientEmail = System.getenv("RECIPIENT_EMAIL"); // Receiver's email address

        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Allure Test Report");
            message.setText("The test execution has completed. You can view the Allure report here:\n" + allureReportLink);

            // Send email
            Transport.send(message);
            System.out.println("✅ Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

