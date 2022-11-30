package com.amex.api;

import com.amex.api.data.*;
import com.amex.api.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class AmexAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmexAPIApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService products, AssociateService associates, CustomerService customers, OrderService orders, OrderItemService orderItems) {
        return args ->
        {
            // default products
            var apple = products.saveProduct(new Product("Apple", "A delicious red apple from the apple orchards at Morales Farms", new BigDecimal(1.00)));
            var orange = products.saveProduct(new Product("Orange", "A plump, juicy orange from the orange groves at Morales Farms", new BigDecimal(1.00)));
            var melon = products.saveProduct(new Product("Melon", "A ripe melon from the pristine gardens at Morales Farms", new BigDecimal(1.25)));
            var associate = associates.saveAssociate(new Associate("edgar.bon-associate@amex.com", "12345", "Edgar", "Bon-Associate", "917-222-2222"));
            var customer1 = customers.saveCustomer(new Customer("sophie.bon-customer@gmail.com", "Sophie", "Bon-Customer", "917-555-5555"));
            var customer2 = customers.saveCustomer(new Customer("issy.bon-customer@gmail.com", "Isabella", "Bon-Customer", "917-777-7777"));
            var customer3 = customers.saveCustomer(new Customer("sabrina.bon-customer@gmail.com", "Sabrina", "Bon-Customer", "917-888-8888"));


            Order order = orders.saveOrder(new Order(associate, customer1));
            order.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order, apple, 1)), orderItems.saveOrderItem(new OrderItem(order, orange, 1))));
            orders.updateOrder(order, order.getId());

            Order order2 = orders.saveOrder(new Order(associate, customer1));
            order2.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order2, apple, 2)), orderItems.saveOrderItem(new OrderItem(order2, orange, 2))));
            orders.updateOrder(order2, order2.getId());

            Order order3 = orders.saveOrder(new Order(associate, customer1));
            order3.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order3, apple, 3)), orderItems.saveOrderItem(new OrderItem(order3, orange, 3))));
            orders.updateOrder(order3, order3.getId());

            Order order4 = orders.saveOrder(new Order(associate, customer1));
            order4.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order4, apple, 4)), orderItems.saveOrderItem(new OrderItem(order4, orange, 4))));
            orders.updateOrder(order4, order4.getId());

            Order order5 = orders.saveOrder(new Order(associate, customer1));
            order5.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order5, apple, 5)), orderItems.saveOrderItem(new OrderItem(order5, orange, 5))));
            orders.updateOrder(order5, order5.getId());

            Order order6 = orders.saveOrder(new Order(associate, customer1));
            order6.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order6, apple, 6)), orderItems.saveOrderItem(new OrderItem(order6, orange, 6))));
            orders.updateOrder(order6, order6.getId());

            Order order7 = orders.saveOrder(new Order(associate, customer1));
            order7.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order7, apple, 7)), orderItems.saveOrderItem(new OrderItem(order7, orange, 7))));
            orders.updateOrder(order7, order7.getId());

            Order order8 = orders.saveOrder(new Order(associate, customer1));
            order8.setOrderItems(Arrays.asList(orderItems.saveOrderItem(new OrderItem(order8, apple, 8)), orderItems.saveOrderItem(new OrderItem(order8, orange, 8))));
            orders.updateOrder(order8, order8.getId());

            Order order9 = orders.saveOrder(new Order(associate, customer2));
            order9.setOrderItems(Arrays.asList(
                    orderItems.saveOrderItem(new OrderItem(order9, apple, 5)),
                    orderItems.saveOrderItem(new OrderItem(order9, orange, 10)),
                    orderItems.saveOrderItem(new OrderItem(order9, melon, 20))));
            orders.updateOrder(order9, order9.getId());
        };
    }
}
