package com.amex.api.service;

import com.amex.api.data.OrderItem;
import com.amex.api.data.OrderItemPk;
import com.amex.api.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem otherOrderItem, OrderItemPk orderItemPk) {
        OrderItem orderItem = orderItemRepository.findById(orderItemPk).get();
        if (Objects.nonNull(otherOrderItem) && otherOrderItem.getQuantity() > 0) {
            orderItem.setQuantity(otherOrderItem.getQuantity());
        }
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> fetchOrderItemById(OrderItemPk orderItemPk) {
        return orderItemRepository.findById(orderItemPk);
    }

    @Override
    public List<OrderItem> fetchOrderItems() {
        return (List<OrderItem>) orderItemRepository.findAll();
    }

    @Override
    public void deleteOrderItemById(OrderItemPk orderItemPk) {
        orderItemRepository.deleteById(orderItemPk);
    }
}
