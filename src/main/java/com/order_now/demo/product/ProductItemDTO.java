package com.order_now.demo.product;

public record ProductItemDTO(
        String name,
        String description,
        Double price,
        String category
) {
}
