package com.poc.microservice.customer.controller;

import com.poc.microservice.customer.request.CustomerRequest;
import com.poc.microservice.customer.response.CustomerResponse;
import com.poc.microservice.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("account-detail/{accountId}")
    public String getAccountDetail(@PathVariable int accountId) {
        return "This Account Detail";
    }

    @PostMapping("create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setResponse(customerService.createCustomer(customerRequest.getRequest()));
        return ResponseEntity.ok(customerResponse);
    }

}
