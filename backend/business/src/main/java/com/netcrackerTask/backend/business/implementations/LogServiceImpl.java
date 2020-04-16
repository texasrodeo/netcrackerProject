package com.netcrackerTask.backend.business.implementations;

import java.util.Date;
import java.sql.Timestamp;
import com.netcrackerTask.backend.business.entity.Log;
import com.netcrackerTask.backend.business.persistence.LogRepository;
import com.netcrackerTask.backend.business.service.interfaces.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {

    LogRepository logRepository;

    @Autowired
    public LogServiceImpl(final LogRepository logRepository){
        this.logRepository=logRepository;
    }

    public void writeLog(String json, String type) {
        Date  date = new Date();
        Timestamp sqlTime = new Timestamp(date.getTime());
        Log log = new Log(sqlTime, type, json);
        logRepository.save(log);
    }
}
