/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author seifeddine
 */
public class JavaMail extends Thread{
    public String recipient;
    public int role;
    public static String nomform;
    public String UserName;

    @Override
    public void run() {
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            sendMail(recipient, role, UserName);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void sendMail(String recepient,int role,String UserName) throws Exception{
        System.out.println("preparing to");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail= "tanyttanyt9@gmail.com";
        String myAccountPwd= "tanyt2022";
        
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,myAccountPwd);
            }
        });
        
        
            Email(session,myAccountEmail,recepient, UserName);
       
      
        
    }
     private static void Email(Session session,String myAccountEmail,String recepient,String UserName) throws Exception {
        
        try {
            LocalDateTime now =  LocalDateTime.now();
                    
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle reclamation de "+UserName);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <h1>Nouvelle reclamation de "+UserName+" </h1>" +
                "   <p>Bonjour</p>" +
                "   <p>Une nouvelle réclamation est effectué le "+now.getDayOfMonth()+"/"+now.getMonthValue()+"/"+now.getYear()+" de "+UserName+"</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}