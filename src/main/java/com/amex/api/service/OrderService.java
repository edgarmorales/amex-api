package com.amex.api.service;

import com.amex.api.data.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order saveOrder(Order order);

    Order updateOrder(Order order, Long id);

    Optional<Order> fetchOrderById(Long orderId);

    List<Order> fetchOrders();

    void deleteOrderById(Long orderId);
}
