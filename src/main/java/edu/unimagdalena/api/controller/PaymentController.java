package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.entities.dto.PaymentDTO;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.service.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping("")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok().body(payments);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentById(id);
            return ResponseEntity.ok().body(paymentDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/order/{orderId}")
    public ResponseEntity<PaymentDTO> getPaymentsByOrderId(@PathVariable("orderId") Long orderId) {
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(payment);
    }

    @RequestMapping("date-range?startDate={startDate}&endDate={endDate}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByDateRange(@PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
        List<PaymentDTO> payments = paymentService.getPaymentsBetweenDates(startDate, endDate);
        return ResponseEntity.ok().body(payments);
    }

    @PostMapping("")
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO payment = paymentService.create(paymentDTO);
            return ResponseEntity.status(201).body(payment);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable("id") Long id, @RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO payment = paymentService.update(paymentDTO, id);
            return ResponseEntity.ok().body(payment);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            paymentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
