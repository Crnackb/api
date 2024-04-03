package edu.unimagdalena.api.service.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.entities.dto.PaymentDTO;
import edu.unimagdalena.api.entities.enums.PaymentMethod;

public interface PaymentService {
    // CRUD
    PaymentDTO create(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(Long paymentId);

    PaymentDTO update(PaymentDTO paymentDTO, Long paymentId);

    void delete(Long paymentId);

    // Other methods

    List<PaymentDTO> getAllPayments();

    List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<PaymentDTO> getByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod);
}
