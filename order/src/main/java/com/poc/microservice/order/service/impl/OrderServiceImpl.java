package com.poc.microservice.order.service.impl;

import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.order.entity.Order;
import com.poc.microservice.order.publisher.OrderPublisher;
import com.poc.microservice.order.repository.OrderRepository;
import com.poc.microservice.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderPublisher orderPublisher;

    @Override
    public Order createOrder(Order order) {
        log.info("Creating Order {}", order);
        order = orderRepository.save(order);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(order.getId());
        orderDetails.setCustomerId(order.getCustomerId());
        orderDetails.setOrderTotal(order.getOrderTotal());
        orderDetails.setState(order.getState());
        orderPublisher.sendPendingEvent(orderDetails);
        log.info("Sending the pending order {}", orderDetails);
        return order;
    }

    @Override
    public Order updateOrder(OrderDetails orderDetails) {
        log.info("Updating the order from orderDetails {}", orderDetails);
        Order order = orderRepository.findById(orderDetails.getId()).get();
        order.setState(orderDetails.getState());
        order = orderRepository.save(order);
        log.info("Updated the order {}", order);
        return order;
    }
}
