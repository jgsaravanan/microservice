package com.poc.microservice.customer.publisher;

import com.poc.microservice.common.order.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerPublisher {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendApprovalEvent(OrderDetails orderDetail) {
        log.info("Sending Approval Event {}", orderDetail);
        jmsTemplate.convertAndSend("CUSTOMER_QUEUE", orderDetail);
    }

    public void sendRejectionEvent(OrderDetails orderDetail) {
        log.info("Sending Rejection Event {}", orderDetail);
        jmsTemplate.convertAndSend("CUSTOMER_QUEUE", orderDetail);
    }
}
