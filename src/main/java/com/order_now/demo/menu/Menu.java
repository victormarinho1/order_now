package com.order_now.demo.menu;

import com.order_now.demo.category.Category;
import com.order_now.demo.product.Product;
import com.order_now.demo.restaurant.Restaurant;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Menu {

    private Restaurant restaurant;
    private List<Product> products;
    private Category category;
}
