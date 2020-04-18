package com.netcrackerTask.backend.business.implementations;

import com.netcrackerTask.backend.business.service.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {
    /**
     * Sender email.
     *
     */
    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    /**
     * Constructor.
     * @param mailSender spring class for emailing
     */
    @Autowired
    public MailServiceImpl(final JavaMailSender mailSender){
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
