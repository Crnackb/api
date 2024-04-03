package edu.unimagdalena.api.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.unimagdalena.api.entities.Order;
import edu.unimagdalena.api.entities.dto.OrderDTO;
import edu.unimagdalena.api.entities.enums.OrderStatus;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.entities.mapper.OrderMapper;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.service.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order orderSaved = orderRepository.save(orderMapper.orderDtoToOrder(orderDTO));
        return orderMapper.orderToOrderDto(orderSaved);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found"));
        return orderMapper.orderToOrderDto(order);
    }

    // Update only status
    @Override
    public OrderDTO update(OrderDTO orderDTO, Long orderId) {
        Order orderInDb = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found"));
        orderInDb.setStatus(orderDTO.status());
        return orderMapper.orderToOrderDto(orderRepository.save(orderInDb));
    }

    @Override
    public void delete(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotAbleToDeleteException("Order not found, not able to delete"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> customers = orderRepository.findAll();
        return customers.stream()
                .map(orderMapper::orderToOrderDto)
                .toList();
    }

    @Override
    public List<OrderDTO> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findBetweenDates(startDate, endDate);
        return orders.stream().map(orderMapper::orderToOrderDto).toList();
    }

    @Override
    public List<OrderDTO> getByCustomerIdAndStatus(Long customerId, OrderStatus status) {
        List<Order> orders = orderRepository.findByCustomerIdAndStatus(customerId, status);
        return orders.stream().map(orderMapper::orderToOrderDto).toList();
    }

    @Override
    public List<OrderDTO> getOrdersWithItemsByCustomerIdFetch(Long customerId) {
        // List<Order> orders =
        // orderRepository.getOrdersWithItemsByCustomerIdFetch(customerId);
        // return orders.stream().map(orderMapper::orderToOrderDto).toList();
        return null;
    }

}
