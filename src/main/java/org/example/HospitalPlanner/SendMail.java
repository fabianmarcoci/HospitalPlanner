package org.example.HospitalPlanner;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private final String username = System.getenv("MAIL_USER");
    private final String password = System.getenv("MAIL_PASSWORD");
    private String toEmail;

    public SendMail(String toEmail) {
        this.toEmail = toEmail;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.toEmail));
            message.setSubject("Forgot your password?");
            message.setText("Hello, this is a link to reset your password: " + "-_-");

            Transport.send(message);

            System.out.println("Email Sent Successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
