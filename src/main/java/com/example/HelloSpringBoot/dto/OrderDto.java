package com.example.HelloSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter@Setter
public class OrderDto {
    @NotNull(message = "상품 아이디는 필수임요")
    private Long itemId;

    @Min(value=1, message = "최소 1개 이상 시키세요")
    @Max(value = 99, message = "최대 99개까지만 주문 가능")
    private int count;

}
