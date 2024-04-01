package edu.unimagdalena.api.entity.dto;

public record ShipmentDetailsDTO(
                Long id,
                Long orderId,
                String shipmentAddress,
                String transporter,
                Long guideNumber) {
}