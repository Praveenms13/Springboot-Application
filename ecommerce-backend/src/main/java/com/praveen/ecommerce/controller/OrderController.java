package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.OrderRequestDto;
import com.praveen.ecommerce.dto.OrderResponseDto;
import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        iOrderService.createOrder(orderRequestDto);
        return ResponseEntity.ok("Order created successfully!");
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllCustomerOrders() {
        return ResponseEntity.ok((List<OrderResponseDto>) iOrderService.getAllCustomerOrders());
    }
}
