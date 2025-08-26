package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.OrderRequestDto;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequestDto);
}
