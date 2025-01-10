package com.order_now.demo.core.authentication;

public record LoginDTO(
        String email,
        String password
) {
}