package com.praveen.ecommerce.service.impl;

import com.praveen.ecommerce.constants.ApplicationConstants;
import com.praveen.ecommerce.dto.OrderRequestDto;
import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.entity.Order;
import com.praveen.ecommerce.entity.OrderItem;
import com.praveen.ecommerce.entity.Product;
import com.praveen.ecommerce.exception.ResourceNotFoundException;
import com.praveen.ecommerce.repository.OrderRepository;
import com.praveen.ecommerce.repository.ProductRepository;
import com.praveen.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<OrderItem> orderItems = orderRequestDto.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductID", item.productId().toString()));
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            return orderItem;
        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }
}
