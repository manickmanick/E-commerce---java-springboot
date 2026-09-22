package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.UserClient;
import com.ecommerce.order_service.dto.CreateOrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;
import com.ecommerce.order_service.dto.UserResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderStatus;
import com.ecommerce.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final UserClient userClient;
    private final OrderRepository orderRepository;

    public OrderService(UserClient userClient,OrderRepository orderRepository) {

        this.userClient = userClient;
        this.orderRepository = orderRepository;

    }

    public OrderResponse createOrder(CreateOrderRequest request){
        // Verify that the user exists
        userClient.getUserById(request.userId());

        Order order = new Order();

        order.setUserId(request.userId());
        order.setProductId(request.productId());
        order.setQuantity(request.quantity());
        order.setStatus(OrderStatus.CREATED);

        LocalDateTime now = LocalDateTime.now();

        order.setCreatedAt(now);
        order.setUpdatedAt(now);

        Order savedOrder = orderRepository.save(order);

        return toResponse(savedOrder);

    }

    public UserResponse getUser(Long userId) {
        return userClient.getUserById(userId);
    }

    private OrderResponse toResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }


//    @CircuitBreaker(name="userService",fallbackMethod = "userServiceFallback")
//    public UserResponse getUser(Long userId) {
//        return userClient.getUserById(userId);
//    }
//
//    public UserResponse userServiceFallback(Long userId,Throwable throwable){
//        throw new RuntimeException(
//                "User Service is currently unavailable"
//        );
//    }
}
