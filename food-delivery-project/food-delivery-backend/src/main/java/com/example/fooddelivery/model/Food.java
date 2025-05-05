
package com.example.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
}
