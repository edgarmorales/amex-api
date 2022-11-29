package com.amex.api.service;

import com.amex.api.data.OrderItem;
import com.amex.api.data.OrderItemPk;
import com.amex.api.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public OrderItem updateOrder(OrderItem otherOrderItem, OrderItemPk orderItemPk) {
        OrderItem orderItem = orderItemRepository.findById(orderItemPk).get();
        // update the orderItem
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
