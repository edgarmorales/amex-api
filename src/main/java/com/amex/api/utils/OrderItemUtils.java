package com.amex.api.utils;

import com.amex.api.data.Product;

import java.math.BigDecimal;

public class OrderItemUtils {

    protected static final String PRODUCT_NAME_APPLE = "Apple";
    protected static final String PRODUCT_NAME_ORANGE = "Orange";

    public static BigDecimal CalculateDiscountPrice(Product product, Integer quantity) {

        BigDecimal price = product.getPrice();

        return switch (product.getName()) {
            /**
             * If the product is an apple, then apply a "2 for 1" discount strategy
             * against the requested quantity and return a final discount price.
             */
            case PRODUCT_NAME_APPLE -> CalculateTwoForOnePriceStrategy(quantity, price);
            /**
             * If the product is an Orange, then apply the "3 for the price of 2"
             * discount strategy against the requested quantity and return a final
             * discount price.
             */
            case PRODUCT_NAME_ORANGE -> CalculateThreeForTwoPriceStrategy(quantity, price);
            /**
             * Otherwise, return standard net price.
             */
            default -> CalculateNetPriceStrategy(quantity, price);
        };

    }

    public static BigDecimal CalculateNetPriceStrategy(Integer quantity, BigDecimal price) {
        return price.multiply(new BigDecimal((quantity)));
    }

    public static BigDecimal CalculateTwoForOnePriceStrategy(Integer quantity, BigDecimal listPrice) {
        BigDecimal sum = new BigDecimal(0.00);
        for (int i = 1; i <= quantity; i++) {
            if (i % 2 == 0) {
                continue;
            }
            sum = sum.add(listPrice);
        }
        return sum;
    }

    public static BigDecimal CalculateThreeForTwoPriceStrategy(Integer quantity, BigDecimal listPrice) {
        BigDecimal sum = new BigDecimal(0.00);
        for (int i = 1; i <= quantity; i++) {
            if (i % 3 == 0) {
                continue;
            }
            sum = sum.add(listPrice);
        }
        return sum;
    }
}
