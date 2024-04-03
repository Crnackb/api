package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.entities.dto.OrderItemDTO;

public interface OrderItemService {

    // CRUD

    OrderItemDTO create(OrderItemDTO orderItemDTO);

    OrderItemDTO getOrderItemById(Long orderItemId);

    OrderItemDTO update(OrderItemDTO orderItemDTO, Long orderItemId);

    void delete(Long orderItemId);

    // Others methods

    List<OrderItemDTO> getAllOrderItems();

    List<OrderItemDTO> getOrderItemsByOrderId(Long orderId);

    List<OrderItemDTO> getOrderItemsByProductId(Long productId);

    Float calculateTotalSalesForProduct(String nameProduct);

}
