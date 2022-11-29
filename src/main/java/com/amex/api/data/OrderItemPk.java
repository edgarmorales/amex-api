package com.amex.api.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class OrderItemPk implements Serializable {

    @JsonBackReference
    @JoinColumn(name = "order_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Order order;

    @JoinColumn(name = "product_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Product product;
}
