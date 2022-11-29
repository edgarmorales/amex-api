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
            var apple = products.saveProduct(new Product("Apple", "A delicious red apple from the apple orchards at Morales Farms", new BigDecimal(0.60)));
            var orange = products.saveProduct(new Product("Orange", "A plump, juicy orange from the orange groves at Morales Farms", new BigDecimal(0.25)));
            var melon = products.saveProduct(new Product("Melon", "A ripe melon from the pristine gardens at Morales Farms", new BigDecimal(1.25)));
            var associate = associates.saveAssociate(new Associate("edgar.bon-associate@amex.com", "12345", "Edgar", "Bon-Associate", "917-222-2222"));
            var customer1 = customers.saveCustomer(new Customer("sophie.bon-customer@gmail.com", "Sophie", "Bon-Customer", "917-555-5555"));
            var customer2 = customers.saveCustomer(new Customer("issy.bon-customer@gmail.com", "Isabella", "Bon-Customer", "917-777-7777"));
            var customer3 = customers.saveCustomer(new Customer("sabrina.bon-customer@gmail.com", "Sabrina", "Bon-Customer", "917-888-8888"));


            Order order = new Order();
            order.setCustomer(customer1);
            order.setAssociate(associate);
            orders.saveOrder(order);
            order.setOrderItems(Arrays.asList(
                    orderItems.saveOrderItem(new OrderItem(order, apple, 10)),
                    orderItems.saveOrderItem(new OrderItem(order, orange, 10))));
            orders.updateOrder(order, order.getId());


            Order order2 = new Order();
            order2.setCustomer(customer2);
            order2.setAssociate(associate);
            orders.saveOrder(order2);
            order2.setOrderItems(Arrays.asList(
                    orderItems.saveOrderItem(new OrderItem(order2, apple, 5)),
                    orderItems.saveOrderItem(new OrderItem(order2, orange, 10)),
                    orderItems.saveOrderItem(new OrderItem(order2, melon, 20))));
            orders.updateOrder(order2, order2.getId());
        };
    }
}
