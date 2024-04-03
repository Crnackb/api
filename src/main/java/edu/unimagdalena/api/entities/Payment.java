package edu.unimagdalena.api.entity;

import java.time.LocalDateTime;

import edu.unimagdalena.api.entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Float totalPayment;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

}
