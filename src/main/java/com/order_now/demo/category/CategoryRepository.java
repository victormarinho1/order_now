package com.order_now.demo.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByRestaurantId(Long id);
    boolean existsByName(String name);
    Optional<Category> findByRestaurantIdAndName(Long id, String name);
}
