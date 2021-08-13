package com.tradeledger.cards.database;

import com.tradeledger.cards.database.entities.RequestLogs;
import com.tradeledger.cards.database.repo.RequestLogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RequestLogsService {

    @Autowired
    private RequestLogsRepo requestLogsRepo;

    public RequestLogs saveNewRequestLogs(String fullName, String emailAddress, String address) {
        RequestLogs newRequestLog = new RequestLogs();
        newRequestLog.setFullName(fullName);
        newRequestLog.setEmailAddress(emailAddress);
        newRequestLog.setAddress(address);
        newRequestLog.setRequestTimestamp(LocalDateTime.now());
        requestLogsRepo.save(newRequestLog);
        return newRequestLog;
    }

    public RequestLogs findRequestLogsById(long requestLogId) {
        Optional<RequestLogs> logById = requestLogsRepo.findById(requestLogId);
        if(!logById.isPresent()){
            throw new IllegalArgumentException("Log Id : " + logById + " not found");
        }
        return logById.get();
    }

    public RequestLogs updateRequestLogs(RequestLogs requestLog) {
        return requestLogsRepo.saveAndFlush(requestLog);
    }

}
