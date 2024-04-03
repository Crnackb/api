package edu.unimagdalena.api.entities.dto;

public record OrderItemDTO(
        Long id,
        Long orderId,
        Long productId,
        Integer amount,
        Float unitPrice) {

}
