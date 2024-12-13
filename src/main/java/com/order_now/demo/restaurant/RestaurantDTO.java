package com.order_now.demo.restaurant;

public record RestaurantDTO(
        Long id,
        String name,
        String phone,
        String email,
        String url_img,
        Boolean enabled
) {
}
