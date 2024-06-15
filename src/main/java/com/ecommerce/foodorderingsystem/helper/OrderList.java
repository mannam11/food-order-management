package com.ecommerce.foodorderingsystem.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderList {

    private long foodItemId;

    private int quantity;
}
