package edu.unimagdalena.api.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entities.ShipmentDetails;
import edu.unimagdalena.api.entities.dto.ShipmentDetailsDTO;

@Mapper
public interface ShipmentDetailsMapper {

    ShipmentDetailsMapper INSTANCE = Mappers.getMapper(ShipmentDetailsMapper.class);

    @Mapping(source = "orderId", target = "order.id")
    ShipmentDetails shipmentDetailsDtoToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO);

    @Mapping(source = "order.id", target = "orderId")
    ShipmentDetailsDTO shipmentDetailsToShipmentDetailsDto(ShipmentDetails shipmentDetails);
}