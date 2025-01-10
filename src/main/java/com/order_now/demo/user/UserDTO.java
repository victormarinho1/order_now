package com.order_now.demo.user;

public record UserDTO(
        Long id,
        String name,
        String email,
        String role,
        Boolean enabled
) {
}