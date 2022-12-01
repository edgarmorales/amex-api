package com.amex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
    @NotNull @JsonProperty("product_id")
    private Long productId;
    @NotNull
    private Integer quantity;
}
