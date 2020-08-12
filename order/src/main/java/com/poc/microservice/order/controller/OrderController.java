package com.poc.microservice.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.microservice.order.endpoints.ServiceEndPoints;
import com.poc.microservice.order.response.OrderResponse;
import com.poc.microservice.order.service.OrderService;
import com.poc.microservice.order.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("user-account/{accountNumber}")
    public String getUserAccountDetail(@PathVariable int accountNumber) {
        return restTemplate.getForObject(ServiceEndPoints.ACCOUNTS_SERVICE + "/acc/account/account-detail/" + accountNumber, String.class);
    }

    @PostMapping("create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setData(orderService.createOrder(orderRequest.getData()));
        return ResponseEntity.ok(orderResponse);
    }

}
