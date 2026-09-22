package com.ecommerce.order_service.dto;

public record CreateOrderRequest(
        Long userId,
        Long productId,
        Integer quantity
) {
}
