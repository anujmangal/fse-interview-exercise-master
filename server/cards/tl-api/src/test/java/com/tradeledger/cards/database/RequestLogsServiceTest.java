package com.tradeledger.cards.database;

import com.tradeledger.cards.database.entities.RequestLogs;
import com.tradeledger.cards.database.repo.RequestLogsRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RequestLogsServiceTest {

    @Autowired
    private RequestLogsService sut;

    @Autowired
    private RequestLogsRepo requestLogsRepo;

    @Test
    @Order(1)
    void saveNewRequestLogs() {
        RequestLogs requestLogs = this.sut.saveNewRequestLogs("Test Name", "test@test.com", "Test Address");
        assertNotNull(requestLogs);
        assertEquals(1, requestLogs.getId());
    }

    @Test
    @Order(2)
    void findRequestLogsById() {
        RequestLogs requestLogsById = this.sut.findRequestLogsById(1);
        assertNotNull(requestLogsById);
        assertEquals(requestLogsById.getFullName(), "Test Name");
    }

    @Test
    @Order(3)
    void updateRequestLogs() {
        RequestLogs requestLogsById = this.sut.findRequestLogsById(1);
        assertNotNull(requestLogsById);
        assertEquals(requestLogsById.getFullName(), "Test Name");

        requestLogsById.setFullName("Updated Name");
        this.sut.updateRequestLogs(requestLogsById);

        requestLogsById = this.sut.findRequestLogsById(1);
        assertEquals(requestLogsById.getFullName(), "Updated Name");
    }
}