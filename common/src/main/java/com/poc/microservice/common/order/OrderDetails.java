package com.poc.microservice.common.order;

import com.poc.microservice.common.enums.DomainEvent;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetails implements Serializable {
    private int id;
    private int customerId;
    private double orderTotal;
    private DomainEvent state;
}
