package com.netcrackerTask.backend.business.service.interfaces;

public interface IMailService {
    /**
     * Sends an email
     * @param mailTo where do we send email
     * @param subject subject of email
    * @param message email message
     * */
    void send(String mailTo, String subject, String message);
}
