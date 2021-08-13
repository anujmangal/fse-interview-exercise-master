package com.tradeledger.cards.activemq;

import com.tradeledger.cards.database.entities.RequestLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.tradeledger.cards.activemq.ActiveMqConnectionFactoryConfig.ORDER_QUEUE;

@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void send(RequestLogs msg){
        jmsTemplate.convertAndSend(ORDER_QUEUE, msg);
    }
}
