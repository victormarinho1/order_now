package com.order_now.demo.core.authentication;

public record RegisterDTO(
        String name,
        String email,
        String password
) {
}