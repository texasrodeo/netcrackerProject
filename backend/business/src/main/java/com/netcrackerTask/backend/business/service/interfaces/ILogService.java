package com.netcrackerTask.backend.business.service.interfaces;

public interface ILogService {
    /**
     * Writes a log to database
     * @param json a json file
     * @param type type of error
     * */
    void writeLog(String json, String type);
}
