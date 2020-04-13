package com.netcrackerTask.backend.business.service.implementations;

import com.netcrackerTask.backend.business.service.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;




@Service
public class MailService implements IMailService {
    @Value("${spring.mail.username}")
    private String username;


   private final JavaMailSender mailSender;

   @Autowired
   public MailService(final JavaMailSender mailSender){
       this.mailSender = mailSender;
   }

   public void send(String mailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
   }
}
