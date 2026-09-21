package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.UserClient;
import com.ecommerce.order_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserClient userClient;

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }


    @CircuitBreaker(name="userService",fallbackMethod = "userServiceFallback")
    public UserResponse getUser(Long userId) {
        return userClient.getUserById(userId);
    }

    public UserResponse userServiceFallback(Long userId,Throwable throwable){
        throw new RuntimeException(
                "User Service is currently unavailable"
        );
    }
}
