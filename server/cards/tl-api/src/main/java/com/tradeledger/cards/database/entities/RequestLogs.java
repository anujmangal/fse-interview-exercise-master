package com.tradeledger.cards.database.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class RequestLogs implements Serializable {

    private static final long serialVersionUID= 8443812558521840277L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String emailAddress;

    @NotNull
    private String address;

    @JsonIgnore
    private LocalDateTime requestTimestamp;

    private String responseForCards;

    @JsonIgnore
    private LocalDateTime responseTimestamp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getResponseForCards() {
        return responseForCards;
    }

    public void setResponseForCards(String responseForCards) {
        this.responseForCards = responseForCards;
    }

    public LocalDateTime getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(LocalDateTime responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }
}
