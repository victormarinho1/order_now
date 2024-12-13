package com.order_now.demo.menu;

import com.order_now.demo.product.ProductItemDTO;

import java.util.List;

public record MenuItemDTO(
        List<ProductItemDTO> listProduct
) {
}
