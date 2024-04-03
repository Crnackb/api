package edu.unimagdalena.api.entities.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.entities.enums.OrderStatus;

public record OrderDTO(
        Long id,
        Long customerId,
        LocalDateTime orderDate,
        OrderStatus status) {
}