package com.amex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    @NotNull @JsonProperty("customer_id")
    private Long customerId;
    @NotNull @JsonProperty("associate_id")
    private Long associateId;
    @NotNull @JsonProperty("order_items")
    private List<OrderItemDto> orderItems = new ArrayList<>();
}
