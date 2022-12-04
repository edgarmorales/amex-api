package com.amex.api.service;

import com.amex.api.data.Order;
import com.amex.api.data.OrderItem;
import com.amex.api.repository.OrderItemRepository;
import com.amex.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order otherOrder, Long id) {

        Optional<Order> possibleOrder = orderRepository.findById(id);
        if (possibleOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order " + id + " does not exist");
        }

        Order order = possibleOrder.get();

        if (Objects.nonNull(otherOrder.getCustomer()) &&
                !order.getCustomer().equals(otherOrder.getCustomer())) {
            order.setCustomer(otherOrder.getCustomer());
        }
        if (Objects.nonNull(otherOrder.getAssociate()) &&
                !order.getAssociate().equals(otherOrder.getAssociate())) {
            order.setAssociate(otherOrder.getAssociate());
        }

        if (Objects.nonNull(otherOrder.getOrderItems())) {
            orderItemRepository.deleteAll(order.getOrderItems());
            order.setOrderItems(null);
            for (OrderItem item : otherOrder.getOrderItems()) {
                order.addOrderItem(
                        orderItemRepository.save(
                                new OrderItem(order, item.getProduct(), item.getQuantity())));
            }
        }

        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> fetchOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> fetchOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
