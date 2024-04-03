package edu.unimagdalena.api.service.implementations;

import java.util.List;

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
    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItemDTO create(OrderItemDTO orderItemDTO) {
        OrderItem orderItemSaved = orderItemRepository.save(orderItemMapper.orderItemDtoToOrderItem(orderItemDTO));
        return orderItemMapper.orderItemToOrderItemDto(orderItemSaved);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        return orderItemMapper.orderItemToOrderItemDto(orderItem);
    }

    @Override
    public OrderItemDTO update(OrderItemDTO orderItemDTO, Long orderItemId) {
        OrderItem orderItemInDb = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        orderItemInDb.setAmount(orderItemDTO.amount());
        orderItemInDb.setUnitPrice(orderItemDTO.unitPrice());
        return orderItemMapper.orderItemToOrderItemDto(orderItemRepository.save(orderItemInDb));
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
        return orderItems.stream().map(orderItemMapper::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream().map(orderItemMapper::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByProductId(Long productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderItems.stream().map(orderItemMapper::orderItemToOrderItemDto).toList();
    }

    @Override
    public Float calculateTotalSalesForProduct(String nameProduct) {
        return orderItemRepository.calculateTotalSalesForProduct(nameProduct);
    }

}
