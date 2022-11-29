package com.amex.api.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.amex.api.utils.OrderItemUtils.CalculateDiscountPrice;
import static com.amex.api.utils.OrderItemUtils.CalculateNetPriceStrategy;

@Entity(name = "orderItems")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    @JsonIgnore
    private OrderItemPk pk;

    private int quantity;

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    public BigDecimal getTotalProductNetPrice() {
        return CalculateNetPriceStrategy(getQuantity(), getProduct().getPrice());
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    public BigDecimal getTotalProductDiscountPrice() {
        return CalculateDiscountPrice(getProduct(), getQuantity());
    }

    public OrderItem(Order order, Product product, Integer quantity) {
        this.pk = new OrderItemPk();
        this.pk.setOrder(order);
        this.pk.setProduct(product);
        this.quantity = quantity;
    }
}