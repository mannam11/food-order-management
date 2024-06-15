package com.ecommerce.foodorderingsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderFoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fooditem_id",nullable = false)
    private FoodItem foodItem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orders_id",nullable = false)
    private Orders order;

    private Integer quantity;
}
