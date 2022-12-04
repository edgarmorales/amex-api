package com.amex.api.controller;

import com.amex.api.data.*;
import com.amex.api.dto.OrderDto;
import com.amex.api.dto.OrderItemDto;
import com.amex.api.service.*;
import com.amex.api.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?>  saveOrder(@RequestBody OrderDto dto) {
        try {
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

            return ResponseUtils
                    .generateResponse("Successfully created order.", HttpStatus.CREATED, order);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseUtils
                    .generateResponse("There was an error retrieving orders." + ex.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        try {
            return ResponseUtils
                    .generateResponse("Successfully retrieved orders.",
                            HttpStatus.OK, orderService.fetchOrders());

        } catch (Exception ex) {
            return ResponseUtils
                    .generateResponse("There was an error retrieving orders.",
                            HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDto dto, @PathVariable("id") Long id) {
        try {
            Optional<Order> possibleOrder = orderService.fetchOrderById(id);

            if (possibleOrder.isEmpty()) {
                return ResponseUtils
                        .generateResponse("Order not found.",
                                HttpStatus.BAD_REQUEST, null);
            }

            Optional<Customer> customer = customerService.fetchCustomerById(dto.getCustomerId());
            if (customer.isEmpty()) {
                throw new ResourceNotFoundException("Customer not found.");
            }

            Optional<Associate> associate = associateService.fetchAssociateById(dto.getAssociateId());
            if (associate.isEmpty()) {
                throw new ResourceNotFoundException("Associate not found.");
            }

            Order order = new Order();
            order.setCustomer(customer.get());
            order.setAssociate(associate.get());

            for (OrderItemDto item : dto.getOrderItems()) {
                Optional<Product> product = productService.fetchProductById(item.getProductId());
                if (product.isEmpty()) {
                    throw new ResourceNotFoundException("Product not found.");
                }
                order.addOrderItem(new OrderItem(order, product.get(), item.getQuantity()));
            }

            return ResponseUtils
                    .generateResponse("Successfully updated order.",
                            HttpStatus.OK, orderService.updateOrder(order, id));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseUtils
                    .generateResponse("There was an error retrieving order: " + ex.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        try {
            Optional<Order> possibleOrder = orderService.fetchOrderById(id);
            if (possibleOrder.isPresent()) {
                return ResponseUtils
                        .generateResponse("Successfully retrieved order.",
                                HttpStatus.OK, possibleOrder.get());
            } else {
                return ResponseUtils
                        .generateResponse("Order not found.",
                                HttpStatus.BAD_REQUEST, null);
            }
        } catch (Exception ex) {
            return ResponseUtils
                    .generateResponse("There was an error retrieving the order.",
                            HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
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
