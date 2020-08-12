package com.poc.microservice.customer.service.impl;

import com.poc.microservice.common.enums.DomainEvent;
import com.poc.microservice.common.order.OrderDetails;
import com.poc.microservice.customer.entity.Customer;
import com.poc.microservice.customer.repository.CustomerRepository;
import com.poc.microservice.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public OrderDetails reserveCreditLimit(OrderDetails orderDetail) {
        log.info("reserve the credit limit for the customer associated with the order {}", orderDetail);
        Customer customer = customerRepository.findById(orderDetail.getCustomerId()).get();
        log.info("credit limit {}", customer);
        if (customer.getCreditLimit() > orderDetail.getOrderTotal()) {
            customer.setCreditLimit(customer.getCreditLimit() - orderDetail.getOrderTotal());
            orderDetail.setState(DomainEvent.APPROVED);
            customerRepository.save(customer);
        } else {
            orderDetail.setState(DomainEvent.REJECTED);
        }
        log.info("updated credit limit {}", customer);
        return orderDetail;
    }
}
