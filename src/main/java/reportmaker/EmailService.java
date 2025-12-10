/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService implements INotificationService {


    private final String username = "andrew.wijaya.130@gmail.com"; 
    
  
    private final String password = "bkaz oqhq wtvc itde"; 

    @Override
    public void sendNotification(String recipient, String reportContent) {
        System.out.println("   >>> [Email] Preparing to send to " + recipient + "...");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        
        // Fixes for common network/SSL errors in student labs
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "*");

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Your Academic Transcript (Official)");
            message.setText("Dear Student,\n\nHere is your requested academic report:\n\n" + reportContent + "\n\nRegards,\nAcademic Officer");

            Transport.send(message);
            System.out.println("   >>> [Email] Sent successfully!");
            
        } catch (MessagingException e) {
            System.out.println("   >>> [Error] Email failed: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}