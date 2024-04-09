package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.entities.dto.OrderItemDTO;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.service.services.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order-items")
public class OrderItemController {
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok().body(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable("id") Long id) {
        try {
            OrderItemDTO orderItemDTO = orderItemService.getOrderItemById(id);
            return ResponseEntity.ok().body(orderItemDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<OrderItemDTO> create(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItemDTO orderItemSaved = orderItemService.create(orderItemDTO);
        return ResponseEntity.status(201).body(orderItemSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> update(@PathVariable("id") Long id, @RequestBody OrderItemDTO orderItemDTO) {
        try {
            OrderItemDTO orderItem = orderItemService.update(orderItemDTO, id);
            return ResponseEntity.ok().body(orderItem);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            orderItemService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
