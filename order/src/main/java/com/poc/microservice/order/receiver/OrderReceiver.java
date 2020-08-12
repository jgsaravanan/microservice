package com.poc.microservice.order.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.order.entity.Order;
import com.poc.microservice.order.service.OrderService;
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
public class OrderReceiver {

    @Autowired
    private OrderService orderService;

    @JmsListener(destination = "CUSTOMER_QUEUE", containerFactory = "myFactory")
    public void receiveOrderEvent(Message orderEvent) throws JMSException, IOException {
        log.info("Receiving the orderDetails {}", orderEvent);
        OrderDetails orderDetails = new ObjectMapper().readValue(((TextMessage) orderEvent).getText(), OrderDetails.class);
        Order order = orderService.updateOrder(orderDetails);
        log.info("Updated the order {}", order);
    }
}
