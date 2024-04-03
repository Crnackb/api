package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.entities.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.entities.enums.OrderStatus;

public interface ShipmentDetailsService {

    // CRUD
    ShipmentDetailsDTO create(ShipmentDetailsDTO shipmentDetailDTO);

    ShipmentDetailsDTO getShipmentDetailById(Long shipmentDetailId);

    ShipmentDetailsDTO update(ShipmentDetailsDTO shipmentDetailDTO, Long shipmentDetailId);

    void delete(Long shipmentDetailId);

    // Others methods

    List<ShipmentDetailsDTO> getAllShipmentDetails();

    ShipmentDetailsDTO getByOrderId(Long orderId);

    List<ShipmentDetailsDTO> getByTransporter(String transporter);

    List<ShipmentDetailsDTO> getByOrderStatus(OrderStatus orderStatus);

}
