package com.tradeledger.cards.service;

import com.tradeledger.cards.activemq.JmsProducer;
import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.database.RequestLogsService;
import com.tradeledger.cards.database.entities.RequestLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantEligibilityService {

    @Autowired
    private RequestLogsService requestLogsService;

    @Autowired
    private JmsProducer jmsProducer;

    public long processEligibilityRequest(final Applicant applicant) {
        RequestLogs requestLogs = this.requestLogsService.saveNewRequestLogs(applicant.getName(), applicant.getEmail(), applicant.getAddress());
        jmsProducer.send(requestLogs);
        return requestLogs.getId();
    }

    public String checkEligibilityResponse(final long requestId) {
        RequestLogs requestLogs = this.requestLogsService.findRequestLogsById(requestId);
        if (requestLogs.getResponseTimestamp() != null){
            return requestLogs.getResponseForCards();
        }
        else{
            return "-1";
        }
    }
}
