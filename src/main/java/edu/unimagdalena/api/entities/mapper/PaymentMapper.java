package edu.unimagdalena.api.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entities.Payment;
import edu.unimagdalena.api.entities.dto.PaymentDTO;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "orderId", target = "order.id")
    Payment paymentDtoToPayment(PaymentDTO paymentDTO);

    @Mapping(source = "order.id", target = "orderId")
    PaymentDTO paymentToPaymentDto(Payment payment);
}
