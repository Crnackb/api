package edu.unimagdalena.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.api.entity.Order;
import edu.unimagdalena.api.entity.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN ?1 AND ?2")
    List<Order> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o FROM Order o WHERE o.customer.id = ?1 AND o.status = ?2")
    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);

    // @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE
    // o.customerId.id = ?1")
    // @Query("SELECT o FROM Order o JOIN FETCH o.orderItems oi WHERE o.id = ?1")
    // List<Order> findOrdersWithItemsByCustomerIdFetch(Long customerId);

}
