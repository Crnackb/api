package edu.unimagdalena.api.service.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.entities.dto.OrderDTO;
import edu.unimagdalena.api.entities.enums.OrderStatus;

public interface OrderService {
    // CRUD
    OrderDTO create(OrderDTO orderDTO);

    OrderDTO getOrderById(Long orderId);

    OrderDTO update(OrderDTO orderDTO, Long orderId);

    void delete(Long orderId);

    // Other methods

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByCustomerId(Long customerId);

    List<OrderDTO> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDTO> getByCustomerIdAndStatus(Long customerId, OrderStatus status);
}
