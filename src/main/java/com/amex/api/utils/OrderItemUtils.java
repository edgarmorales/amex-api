package com.amex.api.utils;

import com.amex.api.data.Product;

import java.math.BigDecimal;

public class OrderItemUtils {

    public static BigDecimal CalculateDiscountPrice(Product product, Integer quantity) {
        BigDecimal price = product.getPrice();
        return CalculateNetPriceStrategy( quantity, price );
    }

    public static BigDecimal CalculateNetPriceStrategy(Integer quantity, BigDecimal price) {
        return price.multiply(new BigDecimal((quantity)));
    }
}
