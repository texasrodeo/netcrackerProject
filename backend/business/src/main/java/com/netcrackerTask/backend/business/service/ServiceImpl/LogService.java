package com.netcrackerTask.backend.business.service.ServiceImpl;



import com.netcrackerTask.backend.business.entity.Log;
import com.netcrackerTask.backend.business.persistence.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    LogRepository logRepository;

    @Autowired
    public LogService(final LogRepository logRepository){
        this.logRepository=logRepository;
    }

    public void writeLog(String json){
        java.util.Date date=new java.util.Date();
        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
        Log log = new Log(sqlTime,"PaypalPay",json);
        logRepository.save(log);
    }
}
