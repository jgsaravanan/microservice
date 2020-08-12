package com.poc.microservice.customer.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.microservice.common.enums.DomainEvent;
import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.customer.publisher.CustomerPublisher;
import com.poc.microservice.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@Slf4j
@Service
public class CustomerReceiver {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerPublisher customerPublisher;

    @JmsListener(destination = "ORDER_QUEUE", containerFactory = "myFactory")
    public void receiveOrderEvent(Message orderDetail) throws JMSException, IOException {
        log.info("receiving the pending order {}", orderDetail);
        OrderDetails orderEvent = new ObjectMapper().readValue(((TextMessage) orderDetail).getText(), OrderDetails.class);
        log.info("order {}", orderDetail);
        OrderDetails order = customerService.reserveCreditLimit(orderEvent);
        if (order.getState() == DomainEvent.APPROVED) {
            customerPublisher.sendApprovalEvent(order);
        } else {
            customerPublisher.sendRejectionEvent(order);
        }
        log.info("order status {}", order);
    }

}
