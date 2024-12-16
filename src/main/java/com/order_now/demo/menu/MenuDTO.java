package com.order_now.demo.menu;

import com.order_now.demo.product.Product;

import java.util.List;

public record MenuDTO(
        String restaurant,
        String category,
        List<Product> listProduct
) {
}
