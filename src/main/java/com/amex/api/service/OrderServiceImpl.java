package com.amex.api.service;

import com.amex.api.data.Order;
import com.amex.api.repository.OrderItemRepository;
import com.amex.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Order order = orderRepository.findById(id).get();

        if (Objects.nonNull(otherOrder.getOrderItems()) &&
                !order.getOrderItems().equals(otherOrder.getOrderItems())) {
            order.setOrderItems(otherOrder.getOrderItems());
        }
        if (Objects.nonNull(otherOrder.getCustomer()) &&
                !order.getCustomer().equals(otherOrder.getCustomer())) {
            order.setCustomer(otherOrder.getCustomer());
        }
        if (Objects.nonNull(otherOrder.getAssociate()) &&
                !order.getAssociate().equals(otherOrder.getAssociate())) {
            order.setAssociate(otherOrder.getAssociate());
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
