package com.tradeledger.cards.controller;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.service.ApplicantEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("applicantEligibility")
@CrossOrigin(origins = "*")
public class ApplicantEligibilityController {

    @Autowired
    private ApplicantEligibilityService service;

    @PostMapping(path = "check", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public long checkEligibility(@Valid @RequestBody Applicant applicant) {
        return service.processEligibilityRequest(applicant);
    }

    @GetMapping(path = "getCards")
    public String getCards(@Valid @RequestParam long reqId) {
        return service.checkEligibilityResponse(reqId);
    }

}
