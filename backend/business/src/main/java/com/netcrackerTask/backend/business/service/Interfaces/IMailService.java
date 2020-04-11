package com.netcrackerTask.backend.business.service.Interfaces;

public interface IMailService {
    void send(String mailTo, String subject, String message);
}
