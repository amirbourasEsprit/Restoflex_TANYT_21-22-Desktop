/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package test;

import java.io.File;
import java.io.IOException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ASUS
 */
public class fileabir {
    public static void main(String[] args) throws MessagingException, IOException {
     Properties properties = new Properties();
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", 587);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.transport.protocl", "smtp");
    Session session = Session.getInstance(properties,new Authenticator(){
        
        @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("projetqtc2021@gmail.com","esprit2021");

    }
    });
    Message message = new MimeMessage(session);
    message.setSubject("email from my java program");
    Address addressTo=new InternetAddress("tasnim.abidi@esprit.tn");
    message.setRecipient (Message.RecipientType. TO, addressTo);
    MimeMultipart multipart = new MimeMultipart();
    
    
    
    MimeBodyPart attachment2 = new MimeBodyPart();
    attachment2.attachFile("D:/img/img.png");
    
    MimeBodyPart messageBodyPart=new MimeBodyPart();
    messageBodyPart.setContent("<hl>Email from my cool program!</h1>","text/html");
                              
    multipart.addBodyPart (messageBodyPart);
   multipart.addBodyPart(attachment2);
    
    message.setContent(multipart);
    Transport.send(message);
    



    }}
