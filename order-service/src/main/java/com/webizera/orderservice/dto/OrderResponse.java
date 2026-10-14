package com.webizera.orderservice.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String orderNumber, String productId, Integer quantity, BigDecimal totalPrice) {
}
