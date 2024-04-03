package edu.unimagdalena.api.entities.dto;

public record ShipmentDetailsDTO(
        Long id,
        Long orderId,
        String shipmentAddress,
        String transporter,
        Long guideNumber) {
}