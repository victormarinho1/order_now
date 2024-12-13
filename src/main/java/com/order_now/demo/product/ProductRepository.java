package com.order_now.demo.product;

import com.order_now.demo.category.Category;
import com.order_now.demo.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByRestaurantId(Long id);
}
