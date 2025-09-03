package com.praveen.ecommerce.repository;

import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Derived Query Methods with JPA
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);
    List<Order> findByOrderStatus(String orderStatus);

    // ---------- Optional Part Starts ----------
    // Own Custom Query Methods with Inline JPQL
    @Query("SELECT o FROM Order o WHERE o.customer = :customer ORDER BY o.createdAt DESC")
    List<Order> findByCustomerOrderByCreatedAtDescJPQL(@Param("customer") Customer customer);
    @Query("SELECT o FROM Order o WHERE o.orderStatus = ?1")
    List<Order> findOrdersByStatusJPQL(String orderStatus);

    // Own Custom Query Methods with Inline Native Query
    @Query(
            value="SELECT * FROM orders o WHERE o.customer_id = :customerId ORDER BY o.createdAt DESC",
            nativeQuery = true
    )
    List<Order> findByCustomerOrderByCreatedAtDescNativeQuery(@Param("customerId") Long customerId);
    @Query(
            value="SELECT * FROM orders o WHERE o.order_status = ?1",
            nativeQuery = true
    )
    List<Order> findOrdersByStatusNativeQuery(String orderStatus);
    // ---------- Optional Part Ends ----------

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus=:orderStatus, o.updatedAt=CURRENT_TIMESTAMP, o.updatedBy=:updatedBy WHERE o.orderId=:orderId")
    void updateOrderStatusByOrderId(@Param("orderId") Long orderId, @Param("orderStatus") String orderStatus, @Param("updatedBy") String updatedBy);
}