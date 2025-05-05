
package com.example.fooddelivery.service;

import com.example.fooddelivery.model.*;
import com.example.fooddelivery.repository.FoodRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(String username, Map<Long,Integer> items){
        User customer = userRepository.findByUsername(username).orElseThrow();
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());

        Set<OrderItem> orderItems = new HashSet<>();
        double total = 0.0;
        for(Map.Entry<Long,Integer> entry: items.entrySet()){
            Food food = foodRepository.findById(entry.getKey()).orElseThrow();
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setFood(food);
            oi.setQuantity(entry.getValue());
            orderItems.add(oi);
            total += food.getPrice()*entry.getValue();
        }
        order.setItems(orderItems);
        order.setTotalPrice(total);
        return orderRepository.save(order);
    }

    public List<Order> listOrdersForDelivery(){
        return orderRepository.findByStatus(OrderStatus.NEW);
    }

    public Order updateStatus(Long orderId, OrderStatus status){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
