package com.ecommerce.order_service.controller;


import com.ecommerce.order_service.client.UserClient;
import com.ecommerce.order_service.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final UserClient userClient;

    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/user/{userId}")
    public UserResponse getUser(@PathVariable Long userId){
        return userClient.getUserById(userId);
    }
}
