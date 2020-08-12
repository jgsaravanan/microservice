package com.poc.microservice.customer.service;

import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.customer.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    OrderDetails reserveCreditLimit(OrderDetails orderDetail);
}
