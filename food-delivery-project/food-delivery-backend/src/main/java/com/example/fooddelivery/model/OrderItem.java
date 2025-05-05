
package com.example.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Food food;

    private int quantity;
}
