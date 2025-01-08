package com.order_now.demo.restaurant;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.enabled = false WHERE r.id = ?1")
    void delete(Long foodId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByName(String name);

    List<Restaurant> findAllByOrderByIdAsc();
}
