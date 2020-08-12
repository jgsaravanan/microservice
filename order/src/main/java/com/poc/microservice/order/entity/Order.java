package com.poc.microservice.order.entity;

import com.poc.microservice.common.enums.DomainEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(schema = "MS", name = "ORDERS")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    int id;
    @Column(name = "CUSTOMER_ID")
    int customerId;
    @Column(name = "ORDER_TOTAL")
    double orderTotal;
    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    DomainEvent state;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderTotal=" + orderTotal +
                ", state=" + state +
                '}';
    }
}
