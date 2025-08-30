package com.praveen.ecommerce.repository;

import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);
    List<Order> findByOrderStatus(String orderStatus);
}