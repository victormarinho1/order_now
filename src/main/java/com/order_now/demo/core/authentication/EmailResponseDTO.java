package com.order_now.demo.core.authentication;

import java.time.LocalDateTime;

public record EmailResponseDTO(
        String message,
        int statusCode,
        String statusMessage,
        LocalDateTime timestamp
) {
}