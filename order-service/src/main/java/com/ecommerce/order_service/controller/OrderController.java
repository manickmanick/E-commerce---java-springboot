package com.ecommerce.order_service.controller;


import com.ecommerce.order_service.client.UserClient;
import com.ecommerce.order_service.dto.UserResponse;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final UserClient userClient;
    private final OrderService orderService;

    public OrderController(UserClient userClient,OrderService orderService) {
        this.userClient = userClient;
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}")
    public UserResponse getUser(@PathVariable Long userId){
        return orderService.getUser(userId);
    }
}
