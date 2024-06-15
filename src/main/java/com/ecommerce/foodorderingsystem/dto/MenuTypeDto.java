package com.ecommerce.foodorderingsystem.dto;

import com.ecommerce.foodorderingsystem.model.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuTypeDto {

    private String name;

    public static MenuTypeDto fromMenuType(MenuType menuType) {

        return MenuTypeDto.builder()
                .name(menuType.getName())
                .build();
    }
}
