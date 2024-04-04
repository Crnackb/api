package edu.unimagdalena.api.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.entities.OrderItem;
import edu.unimagdalena.api.entities.dto.OrderItemDTO;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.entities.mapper.OrderItemMapper;
import edu.unimagdalena.api.repository.OrderItemRepository;
import edu.unimagdalena.api.service.services.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDTO create(OrderItemDTO orderItemDTO) {
        OrderItem orderItemSaved = orderItemRepository.save(OrderItemMapper.INSTANCE.orderItemDtoToOrderItem(orderItemDTO));
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItemSaved);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItem);
    }

    @Override
    public OrderItemDTO update(OrderItemDTO orderItemDTO, Long orderItemId) {
        OrderItem orderItemInDb = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        orderItemInDb.setAmount(orderItemDTO.amount());
        orderItemInDb.setUnitPrice(orderItemDTO.unitPrice());
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItemRepository.save(orderItemInDb));
    }

    @Override
    public void delete(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotAbleToDeleteException("OrderItem not found, not able to delete"));
        orderItemRepository.delete(orderItem);
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByProductId(Long productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public Float calculateTotalSalesForProduct(String productName) {
        return orderItemRepository.calculateTotalSalesForProduct(productName);
    }

}

