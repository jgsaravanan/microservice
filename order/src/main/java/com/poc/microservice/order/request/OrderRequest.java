package com.poc.microservice.order.request;

import com.poc.microservice.order.entity.Order;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    Order data;
}
