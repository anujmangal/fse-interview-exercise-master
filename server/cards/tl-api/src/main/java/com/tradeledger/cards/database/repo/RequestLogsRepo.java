package com.tradeledger.cards.database.repo;

import com.tradeledger.cards.database.entities.RequestLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogsRepo extends JpaRepository<RequestLogs, Long> {

}