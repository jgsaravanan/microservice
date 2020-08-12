package com.poc.microservice.common.event;

import com.poc.microservice.common.enums.DomainEvent;
import com.poc.microservice.common.order.OrderDetails;
import lombok.Data;

@Data
public class PendingEvent {

    private int id;
    private int customerId;
    private double orderTotal;
    private DomainEvent state;

    public PendingEvent(OrderDetails order) {
        this.id = order.getId();
        this.customerId = order.getCustomerId();
        this.state = order.getState();
        this.orderTotal = order.getOrderTotal();
    }
}
