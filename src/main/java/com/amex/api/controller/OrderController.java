package com.amex.api.controller;

import com.amex.api.data.*;
import com.amex.api.dto.OrderDto;
import com.amex.api.dto.OrderItemDto;
import com.amex.api.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AssociateService associateService;

    @PostMapping("/order")
    public Order saveOrder(@Valid @RequestBody OrderDto dto) {

        Optional<Associate> associate = associateService.fetchAssociateById(dto.getAssociateId());
        if (associate.isEmpty()) {
            throw new ResourceNotFoundException("Associate not found.");
        }
        Optional<Customer> customer = customerService.fetchCustomerById(dto.getCustomerId());
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found.");
        }

        Order order = orderService.saveOrder(new Order(associate.get(), customer.get()));

        for (OrderItemDto item : dto.getOrderItems()) {
            Optional<Product> product = productService.fetchProductById(item.getProductId());
            if (product.isEmpty()) {
                throw new ResourceNotFoundException("Product not found.");
            }
            order.addOrderItem(
                    orderItemService.saveOrderItem(new OrderItem(order, product.get(), item.getQuantity())));
        }
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
