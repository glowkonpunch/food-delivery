
package com.example.fooddelivery.controller;

import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderStatus;
import com.example.fooddelivery.service.OrderService;
import com.example.fooddelivery.repository.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService os, OrderRepository or){
        this.orderService = os;
        this.orderRepository = or;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Order create(@AuthenticationPrincipal UserDetails user, @RequestBody Map<Long,Integer> items){
        return orderService.createOrder(user.getUsername(), items);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<Order> my(@AuthenticationPrincipal UserDetails user){
        return orderRepository.findByCustomer_Username(user.getUsername());
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAuthority('DELIVERY')")
    public List<Order> pending(){
        return orderService.listOrdersForDelivery();
    }

    @PostMapping("/{id}/status")
    @PreAuthorize("hasAuthority('DELIVERY')")
    public Order update(@PathVariable Long id, @RequestParam String status){
        return orderService.updateStatus(id, OrderStatus.valueOf(status));
    }
}
