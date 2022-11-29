package com.amex.api.repository;

import com.amex.api.data.OrderItem;
import com.amex.api.data.OrderItemPk;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPk> {}
