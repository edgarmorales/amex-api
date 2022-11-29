package com.amex.api.service;

import com.amex.api.data.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AssociateService associateService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;

    @Test
    @DisplayName("Create an Order")
    public void test_a_givenOrderService_whenOrderIsCreated_thenFetchAndMatchFields() {

        /* create a order */
        Associate associate = associateService.saveAssociate(new Associate("test-a-associate@test.com", "12345", "Tester", "McTester", "123-456-7890"));
        Customer customer = customerService.saveCustomer(new Customer("test-a-customer@test.com", "TesterA", "McTester", "123-456-7890"));
        Product product = productService.saveProduct(new Product("Test A Product", "A Test A non-valid Product", new BigDecimal("0.10")));
        Order order = new Order(associate, customer);
        orderService.saveOrder(order);

        /* append order items */
        OrderItem orderItem = orderItemService.saveOrderItem(new OrderItem(order, product, 10));
        order.addOrderItem(orderItem);
        orderService.updateOrder(order, order.getId());

        Optional<Order> possibleOrder = orderService.fetchOrderById(order.getId());
        Assertions.assertThat(possibleOrder.isPresent()).isTrue();

        Order refreshedOrder = possibleOrder.get();
        Assertions.assertThat(refreshedOrder.getAssociate()).isNotNull();
        Assertions.assertThat(refreshedOrder.getCustomer()).isNotNull();
        Assertions.assertThat(refreshedOrder.getOrderItems()).hasSize(1);

        Assertions.assertThat(refreshedOrder.getTotalOrderNetPrice()).isEqualTo(new BigDecimal("1.00"));
        Assertions.assertThat(refreshedOrder.getTotalDiscountedPrice()).isEqualTo(new BigDecimal("1.00"));

        OrderItem refreshedOrderItem = refreshedOrder.getOrderItems().stream().findFirst().get();
        Assertions.assertThat(refreshedOrderItem.getProduct()).isNotNull();
        Assertions.assertThat(refreshedOrderItem.getProduct().getName()).isEqualTo("Test A Product");
        Assertions.assertThat(refreshedOrderItem.getProduct().getPrice()).isEqualTo(new BigDecimal("0.10"));
    }

    @Test
    @DisplayName("Delete an Order")
    public void test_b_givenOrderService_whenOrderIsDeleted_thenConfirmOrderNotExists() {
        /* create a order */
        Associate associate = associateService.saveAssociate(new Associate("test-b-associate@test.com", "12345", "TesterB", "McTester", "123-456-7890"));
        Customer customer = customerService.saveCustomer(new Customer("test-b-customer@test.com", "TesterB", "McTester", "123-456-7890"));
        Product product = productService.saveProduct(new Product("Test B Product", "A Test B non-valid Product", new BigDecimal("0.10")));
        Order order = new Order(associate, customer);
        orderService.saveOrder(order);
        Long orderId = order.getId();

        /* append order items */
        OrderItem orderItem = orderItemService.saveOrderItem(new OrderItem(order, product, 10));
        order.addOrderItem(orderItem);
        orderService.updateOrder(order, orderId);

        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getOrderItems()).isNotNull();
        Assertions.assertThat(order.getOrderItems()).hasSize(1);

        orderItemService.deleteOrderItemById(orderItem.getPk());
        orderService.deleteOrderById(orderId);

        Assertions.assertThat(orderService.fetchOrderById(orderId).isPresent()).isFalse();
    }
}
