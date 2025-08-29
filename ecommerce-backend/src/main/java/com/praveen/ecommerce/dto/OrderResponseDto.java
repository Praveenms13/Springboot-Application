package com.praveen.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDto(Long orderId, String status, BigDecimal totalPrice, String cratedAt, List<Object> items) {
}
