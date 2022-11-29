package com.amex.api.controller;

import com.amex.api.data.Order;
import com.amex.api.data.OrderItem;
import com.amex.api.service.OrderItemService;
import com.amex.api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/order")
    public Order saveOrder(@Valid Order order) {

        // save initial order
        orderService.saveOrder(order);

        // save order items within
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItemService.saveOrderItem(orderItem);
        }

        // lastly, save order once again.
        return orderService.updateOrder(order, order.getId());
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.fetchOrders();
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(Order order, @PathVariable("id") Long id) {
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItemService.saveOrderItem(orderItem);
        }
        return orderService.updateOrder(order, id);
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        Optional<Order> possibleOrder = orderService.fetchOrderById(id);
        if (possibleOrder.isPresent()) {
            for (OrderItem orderItem : possibleOrder.get().getOrderItems()) {
                orderItemService.deleteOrderItemById(orderItem.getPk());
            }
        }
        orderService.deleteOrderById(id);
        return "Deleted successfully";
    }
}
