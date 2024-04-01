package edu.unimagdalena.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShipmentDetalis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String shipmentAddress;

    @Column(nullable = true)
    private String transporter;

    @Column(unique = true)
    private Long guideNumber;

}
