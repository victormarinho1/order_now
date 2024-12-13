package com.order_now.demo.product;

public record ProductDTO(
        String name,
        String description,
        Double price,
        String category
) {
}
