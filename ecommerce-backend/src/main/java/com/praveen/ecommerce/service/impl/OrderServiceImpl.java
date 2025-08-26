package com.praveen.ecommerce.service.impl;

import com.praveen.ecommerce.constants.ApplicationConstants;
import com.praveen.ecommerce.dto.OrderRequestDto;
import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.entity.Order;
import com.praveen.ecommerce.repository.OrderRepository;
import com.praveen.ecommerce.repository.ProductRepository;
import com.praveen.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProfileServiceImpl profileService;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = profileService.getAuthenticatedCustomer();
        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequestDto, order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
    }
}
