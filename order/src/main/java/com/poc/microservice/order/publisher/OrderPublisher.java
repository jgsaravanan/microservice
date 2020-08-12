package com.poc.microservice.order.publisher;

import com.poc.microservice.common.order.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderPublisher {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendPendingEvent(OrderDetails orderDetails) {
        log.info("Sending pending order {}", orderDetails);
        jmsTemplate.convertAndSend("ORDER_QUEUE", orderDetails);
    }

}
