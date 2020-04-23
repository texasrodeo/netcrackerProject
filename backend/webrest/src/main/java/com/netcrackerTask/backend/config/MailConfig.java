package com.netcrackerTask.backend.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    /**
     * Mail host server
     * */
    @Value("${spring.mail.host}")
    private String host;

    /**
     * Mail host username
     * */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Mail host password
     * */
    @Value("${spring.mail.password}")
    private String password;

    /**
     * Mail host port
     * */
    @Value("${spring.mail.port}")
    private int port;

    /**
     * Mail host protocol
     * */
    @Value("${spring.mail.protocol}")
    private String protocol;

    /**
     * Mail host debug (true or false)
     * */
    @Value("${mail.debug}")
    private String debug;

    /**
     * Mail host auth (enabled or not)
     * */
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    /**
     * Mail host tls enabled or not
     * */
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPort(port);
        mailSender.setPassword(password);
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", enable);
        properties.setProperty("mail.debug", debug);
        return mailSender;
    }
}
