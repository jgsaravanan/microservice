package com.poc.microservice.order.response;

import com.poc.microservice.order.entity.Order;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Order data;
}
