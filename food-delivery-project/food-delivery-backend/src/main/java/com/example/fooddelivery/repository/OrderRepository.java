
package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Username(String username);
    List<Order> findByStatus(OrderStatus status);
}
