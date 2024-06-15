package com.ecommerce.foodorderingsystem.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {

    private String name;

    private int quantity;

    private int price;

}
