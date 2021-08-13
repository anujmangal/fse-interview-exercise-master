package com.tradeledger.cards.activemq;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.ApplicantEligibility;
import com.tradeledger.cards.database.RequestLogsService;
import com.tradeledger.cards.database.entities.RequestLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.tradeledger.cards.activemq.ActiveMqConnectionFactoryConfig.ORDER_QUEUE;

@Component
public class JmsConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${thirdparty.api.url}")
    private String eligibilityCheckUrl;

    @Autowired
    private RequestLogsService requestLogsService;

    @JmsListener(destination = ORDER_QUEUE)
    public void receive(RequestLogs message){

        Applicant applicant = extractApplicantFromRequestLogsObject(message);

        ResponseEntity<ApplicantEligibility> responseEntity = restTemplate.
                postForEntity(eligibilityCheckUrl, applicant, ApplicantEligibility.class);

        ApplicantEligibility body = responseEntity.getBody();
        String eligibleCardString = body.getEligibleCards().isEmpty() ? null :
                body.getEligibleCards().stream().collect(Collectors.joining(","));

        RequestLogs requestLogsById = requestLogsService.findRequestLogsById(message.getId());
        requestLogsById.setResponseTimestamp(LocalDateTime.now());
        requestLogsById.setResponseForCards(eligibleCardString);
        requestLogsService.updateRequestLogs(requestLogsById);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private Applicant extractApplicantFromRequestLogsObject(RequestLogs message) {
        return new Applicant(message.getFullName(), message.getEmailAddress(), message.getAddress());
    }
}