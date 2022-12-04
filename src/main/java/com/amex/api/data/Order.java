package com.amex.api.data;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Getter @Setter @NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Customer customer;

    @NotNull
    @ManyToOne
    private Associate associate;

    @OneToMany(mappedBy = "pk.order", fetch = FetchType.EAGER)
    @Valid
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant dateModified;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    public BigDecimal getTotalOrderNetPrice() {
        BigDecimal sum = new BigDecimal(0.00);
        List<OrderItem> items = getOrderItems();
        for (OrderItem item : items) {
            BigDecimal netPrice = item.getTotalProductNetPrice();
            sum = sum.add(netPrice);
        }
        return sum;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    public BigDecimal getTotalOrderDiscountPrice() {
        BigDecimal sum = new BigDecimal(0.00);
        List<OrderItem> items = getOrderItems();
        for (OrderItem item : items) {
            BigDecimal discountPrice = item.getTotalProductDiscountPrice();
            sum = sum.add(discountPrice);
        }
        return sum;
    }

    public Order(Associate associate, Customer customer, List<OrderItem> orderItems) {
        this.associate = associate;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Order(Associate associate, Customer customer) {
        this.associate = associate;
        this.customer = customer;
        this.orderItems = new ArrayList<>();
    }

    @Transient
    public void addOrderItem(OrderItem orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
