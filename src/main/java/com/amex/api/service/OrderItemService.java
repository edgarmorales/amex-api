package com.amex.api.service;

import com.amex.api.data.OrderItem;
import com.amex.api.data.OrderItemPk;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    OrderItem saveOrderItem(OrderItem orderItem);

    OrderItem updateOrder(OrderItem orderItem, OrderItemPk orderItemPk);

    Optional<OrderItem> fetchOrderItemById(OrderItemPk orderItemPk);

    List<OrderItem> fetchOrderItems();

    void deleteOrderItemById(OrderItemPk orderItemPk);
}
