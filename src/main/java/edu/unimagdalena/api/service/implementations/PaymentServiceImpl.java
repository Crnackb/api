package edu.unimagdalena.api.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.unimagdalena.api.entities.Payment;
import edu.unimagdalena.api.entities.dto.PaymentDTO;
import edu.unimagdalena.api.entities.enums.PaymentMethod;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.entities.mapper.PaymentMapper;
import edu.unimagdalena.api.repository.PaymentRepository;
import edu.unimagdalena.api.service.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO create(PaymentDTO paymentDTO) {
        Payment paymentSaved = paymentRepository.save(paymentMapper.paymentDtoToPayment(paymentDTO));
        return paymentMapper.paymentToPaymentDto(paymentSaved);
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ObjectNotFoundException("Payment not found"));
        return paymentMapper.paymentToPaymentDto(payment);
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO, Long paymentId) {
        Payment paymentInDb = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ObjectNotFoundException("Payment not found"));
        paymentInDb.setTotalPayment(paymentDTO.totalPayment());
        paymentInDb.setPaymentDate(paymentDTO.paymentDate());
        paymentInDb.setPaymentMethod(paymentDTO.paymentMethod());
        return paymentMapper.paymentToPaymentDto(paymentRepository.save(paymentInDb));
    }

    @Override
    public void delete(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotAbleToDeleteException("Payment not found, not able to delete"));
        paymentRepository.delete(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::paymentToPaymentDto)
                .toList();
    }

    @Override
    public List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Payment> payments = paymentRepository.findBetweenDates(startDate, endDate);
        return payments.stream()
                .map(paymentMapper::paymentToPaymentDto)
                .toList();
    }

    @Override
    public List<PaymentDTO> getByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod) {
        List<Payment> payments = paymentRepository.findByOrderIdAndPaymentMethod(orderId, paymentMethod);
        return payments.stream()
                .map(paymentMapper::paymentToPaymentDto)
                .toList();
    }

}
