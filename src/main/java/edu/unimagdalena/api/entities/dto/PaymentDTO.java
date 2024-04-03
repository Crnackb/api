package edu.unimagdalena.api.entities.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.entities.enums.PaymentMethod;

public record PaymentDTO(
                Long id,
                Long orderId,
                Float totalPayment,
                LocalDateTime paymentDate,
                PaymentMethod paymentMethod) {

}
