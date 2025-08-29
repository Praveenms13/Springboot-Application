package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.OrderRequestDto;
import com.praveen.ecommerce.dto.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getAllCustomerOrders();
}
