package com.amex.api.utils;

import com.amex.api.data.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItemUtils {

    protected static final String PRODUCT_NAME_APPLE = "Apple";
    protected static final String PRODUCT_NAME_ORANGE = "Orange";

    public static BigDecimal CalculateDiscountPrice(Product product, Integer quantity) {

        BigDecimal price = product.getPrice();

        switch (product.getName()) {

            case PRODUCT_NAME_APPLE:
                /**
                 * If the product is an apple, then apply a "2 for 1" discount strategy
                 * against the requested quantity and return a final discount price.
                 */
                return quantity > 1 ?
                        CalculateTwoForOnePriceStrategy(quantity, price) :
                        CalculateNetPriceStrategy(quantity, price);

            case PRODUCT_NAME_ORANGE:
                /**
                 * If the product is an Orange, then apply the "3 for the price of 2"
                 * discount strategy against the requested quantity and return a final
                 * discount price.
                 */
                return quantity > 2 ?
                        CalculateThreeForTwoPriceStrategy(quantity, price) :
                        CalculateNetPriceStrategy(quantity,price);

            default:
                return CalculateNetPriceStrategy( quantity, price );
        }

    }

    public static BigDecimal CalculateNetPriceStrategy(Integer quantity, BigDecimal price) {
        return price.multiply(new BigDecimal((quantity)));
    }

    public static BigDecimal CalculateTwoForOnePriceStrategy(Integer quantity, BigDecimal listPrice) {
        return CalculateMultiItemDiscountPrice(listPrice, new BigDecimal("1"), new BigDecimal("2")).multiply(new BigDecimal(quantity));
    }

    public static BigDecimal CalculateThreeForTwoPriceStrategy(Integer quantity, BigDecimal listPrice) {
        return CalculateMultiItemDiscountPrice(listPrice, new BigDecimal("2"), new BigDecimal("3")).multiply(new BigDecimal(quantity));
    }

    private static BigDecimal CalculateMultiItemDiscountPrice(BigDecimal listPrice, BigDecimal unitsAtListPrice, BigDecimal totalUnits) {
        /*
            Source: https://www.calculatorsoup.com/calculators/financial/sale-price-calculator.php

            Multi-Item Discount Formula
            Discounted price per item = (Number of items at list price x list price) / Number of items in discount deal

                Example: Sale is 4 items for the price of 3. Regular list price is $20.

            Multiply number of items at list price, by list price: 3*20 = 60
            You are paying $60 and you'll get 4 items
            The discount price for each item is 60/4 = $15
            With the formula:
                (3*20) / 4 =
                60 /4 = 15

            Buying 4 for 3 at $20 each means you'll spend $60 for 4 items; the per item
            discounted price is $15. If you compare to the non-discounted price of $20,
            you can save $5 per item with this multi-item discount sale.
        */

        BigDecimal listPriceTotal = unitsAtListPrice.multiply(listPrice);
        BigDecimal discountPrice = listPriceTotal.divide(totalUnits, 2, RoundingMode.HALF_EVEN);

        return discountPrice;
    }
}
