package com.order_now.demo.category;

public record CategoryListDTO(
        Long id,
        String name,
        int index,
        Boolean enabled
) {
}
