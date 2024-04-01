package edu.unimagdalena.api.entity.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.entity.enums.PaymentMethod;

public record PaymentDTO(
        Long id,
        Long orderId,
        Float totalPayment,
        LocalDateTime paymentDate,
        PaymentMethod paymentMethod) {

}
