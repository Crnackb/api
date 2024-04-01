package edu.unimagdalena.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.api.entity.ShipmentDetalis;
import edu.unimagdalena.api.entity.enums.OrderStatus;

@Repository
public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetalis, Long> {

    @Query("SELECT s FROM ShipmentDetalis s WHERE s.order.id = ?1")
    ShipmentDetalis findByOrderId(Long orderId);

    @Query("SELECT s FROM ShipmentDetalis s WHERE s.transporter = ?1")
    List<ShipmentDetalis> findByTransporter(String transporter);

    @Query("SELECT s FROM ShipmentDetalis s WHERE s.order.status = ?1")
    List<ShipmentDetalis> findByOrderStatus(OrderStatus orderStatus);

}