package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.entities.ShipmentDetails;
import edu.unimagdalena.api.entities.dto.OrderItemDTO;
import edu.unimagdalena.api.entities.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.service.services.ShipmentDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipment-details")
public class ShipmentDetailsController {
    private ShipmentDetailsService shipmentDetailsService;
    @GetMapping("")
    public ResponseEntity<List<ShipmentDetailsDTO>> getAllShipmentDetails() {
        List<ShipmentDetailsDTO> shipmentDetails = shipmentDetailsService.getAllShipmentDetails();
        return ResponseEntity.ok(shipmentDetails);
    }




    @PostMapping("")
    public ResponseEntity<ShipmentDetailsDTO> create(ShipmentDetailsDTO shipmentDetails) {
        ShipmentDetailsDTO shipmentDetailsDTO = shipmentDetailsService.create(shipmentDetails);
        return ResponseEntity.status(201).body(shipmentDetailsDTO);
    }


}
