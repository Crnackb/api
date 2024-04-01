package edu.unimagdalena.api.entity.dto;

public record OrderItemDTO(
                Long id,
                Long orderId,
                Long productId,
                Integer amount,
                Float unitPrice) {

}
