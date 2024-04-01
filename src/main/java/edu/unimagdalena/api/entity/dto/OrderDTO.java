package edu.unimagdalena.api.entity.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.entity.enums.OrderStatus;

public record OrderDTO(
                Long id,
                Long customerId,
                LocalDateTime orderDate,
                OrderStatus status) {
}