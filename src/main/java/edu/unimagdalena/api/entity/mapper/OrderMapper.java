package edu.unimagdalena.api.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entity.Order;
import edu.unimagdalena.api.entity.dto.OrderDTO;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "shipmentDetalis", ignore = true)
    Order orderDtoToOrder(OrderDTO orderDTO);

    @Mapping(source = "customer.id", target = "customerId")
    OrderDTO orderToOrderDto(Order order);

}