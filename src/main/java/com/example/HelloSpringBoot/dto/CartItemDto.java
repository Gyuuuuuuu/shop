package com.example.HelloSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartItemDto {
    @NotNull(message = "상품 ID는 필수.")
    private Long itemId;
    @Min(value = 1, message = "최소 1개 이상 담")
    private int count;


}
