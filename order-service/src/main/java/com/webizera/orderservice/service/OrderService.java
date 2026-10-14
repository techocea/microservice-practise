package com.webizera.orderservice.service;

import com.webizera.orderservice.dto.OrderRequest;
import com.webizera.orderservice.dto.OrderResponse;
import com.webizera.orderservice.dto.ProductResponse;
import com.webizera.orderservice.model.Order;
import com.webizera.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient restClient;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        ProductResponse product = restClient.get()
                .uri("http://localhost:8082/api/products/" + orderRequest.productId())
                .retrieve()
                .body(ProductResponse.class);

        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + orderRequest.productId());
        }

        BigDecimal totalPrice = product.price().multiply(BigDecimal.valueOf(orderRequest.quantity()));

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .productId(product.id())
                .quantity(orderRequest.quantity())
                .totalPrice(totalPrice)
                .build();

        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getOrderNumber(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                totalPrice
        );
    }
}
