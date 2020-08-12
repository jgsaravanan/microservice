package com.poc.microservice.order.service;

import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.order.entity.Order;

public interface OrderService {
    Order createOrder(Order order);

    Order updateOrder(OrderDetails order);
}
