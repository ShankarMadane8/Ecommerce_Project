package com.Ecommerce.Controller;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.Ecommerce.Entity.Address;
import com.Ecommerce.Entity.Payment;
import com.Ecommerce.Entity.User;

import jakarta.servlet.http.HttpSession;




public class EmailSender {
	
	private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "kanchanborse65@gmail.com"; // Replace with your email
    private static final String PASSWORD = "wfuziukwyzhzlrz"; // Replace with your password
    
    
	public static boolean sendEmail(String email,String msg) {
		Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD); // Set your email and password
            }
        });
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME)); // Set your email address

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // Set recipient email address

            message.setSubject("E-commerce My Application");

            message.setText(msg);
            Transport.send(message);

            System.out.println("Email sent successfully!");
            return true;
        }catch (MessagingException e) {
            //throw new RuntimeException(e);
        	e.printStackTrace();
        	return false;
        } 
	} 
	
	
	
	
     // --------------------------user generate OTP-----------------------------
       public static int generateOtp()
       {
    	   int otp=(int)((Math.random() *9000)+1000);
    	   return otp;
       }
       
    // --------------------------OTP Sent to Mail-----------------------------
       public static boolean sendOtpEmail( int otp, User user) {
           Properties properties = new Properties();
           properties.put("mail.smtp.host", SMTP_HOST);
           properties.put("mail.smtp.port", SMTP_PORT);
           properties.put("mail.smtp.auth", "true");
           properties.put("mail.smtp.starttls.enable", "true");

           Session session = Session.getInstance(properties, new Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(USERNAME, PASSWORD);
               }
           });

           try {
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(USERNAME));
               message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
               message.setSubject("Password Reset OTP");

               // Email body with bold OTP
               String emailBody = "Dear "+user.getName()+" ,<br><br>"
                       + "Please use the below One Time Password (OTP) to reset your password. This will be valid for 1 minute only.<br><br>"
                       + "One Time Password (OTP): <b>" + otp + "</b><br><br><br>"
                       + "If you are unable to change the password within 1 minute of OTP generation, please click on \"Forgot Password\" and continue with the same process again.";

               message.setContent(emailBody, "text/html"); // Set content type to HTML

               Transport.send(message);

               System.out.println("Email sent successfully to: " + user.getEmail());
               return true;
           } catch (MessagingException e) {
               // Log the exception or throw a custom exception with a meaningful message
               e.printStackTrace();
               return false;
           }
       }
       
       public static boolean isOtpExpire(HttpSession session)
       {
    	   LocalDateTime otpCreationTime = (LocalDateTime) session.getAttribute("otpCreationTime");
    	   if(otpCreationTime == null)
    	   {
    		   return true;
    	   }
    	   LocalDateTime currentTime= LocalDateTime.now();
    	   long timeDiff = ChronoUnit.MINUTES.between(otpCreationTime, currentTime);
    	   if(timeDiff>=1)
    	   {
    		   session.removeAttribute("otp");
    		   session.removeAttribute("otpCreationTime");
    	   }
    	   return timeDiff>=1;
       }
	
       // mail send after payment is successfull
       public static boolean paymentProcessingMail(Payment payment, User user, Address address) {
           Properties properties = new Properties();
           properties.put("mail.smtp.host", SMTP_HOST);
           properties.put("mail.smtp.port", SMTP_PORT);
           properties.put("mail.smtp.auth", "true");
           properties.put("mail.smtp.starttls.enable", "true");

           Session session = Session.getInstance(properties, new Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(USERNAME, PASSWORD);
               }
           });

           try {
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(USERNAME));
               message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
               message.setSubject("Order Successfull");

               // Email body with bold OTP
               String emailBody = "Dear "+user.getName()+" ,<br><br>"
                       + "Please use the below One Time Password (OTP) to reset your password. This will be valid for 1 minute only.<br><br>"
                       + "Delivery Address <b>"+address.getAddress() + "</b><br><br><br>"
                       + "Total Amount <b>"+payment.getAmount() + "</b><br><br><br>"
                       + "If you are unable to change the password within 1 minute of OTP generation, please click on \"Forgot Password\" and continue with the same process again.";

               message.setContent(emailBody, "text/html"); // Set content type to HTML

               Transport.send(message);

               System.err.println("Payment Processing sent successfully to: " + user.getEmail());
               return true;
           } catch (MessagingException e) {
               // Log the exception or throw a custom exception with a meaningful message
               e.printStackTrace();
               return false;
           }
       }
                 
     
}
