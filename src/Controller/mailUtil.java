/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Thinkpad
 */
public class mailUtil {
    public static void sendMail(String recepient,String sender,String pass,String subject,String contenu,File f) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = sender;
        //Your gmail password
        String password = pass;

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient,subject,contenu,f);

        //Send mail
         Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String subject,String contenu,File f) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            Multipart emailContent = new MimeMultipart();
	    MimeBodyPart textBodyPart = new MimeBodyPart();
	    textBodyPart.setText(contenu);
            if(f!=null){
	    MimeBodyPart pdfAttachment = new MimeBodyPart();
	    pdfAttachment.attachFile(f);
            emailContent.addBodyPart(pdfAttachment);
            }
	    emailContent.addBodyPart(textBodyPart);
            message.setContent(emailContent);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(mailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
