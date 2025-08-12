package com.example.HelloSpringBoot.dto;

import com.example.HelloSpringBoot.constant.OrderStatus;
import com.example.HelloSpringBoot.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderHistoryDto {
    private Long orderId; //주문아이디
    private String orderDate; //주문 날짜
    private OrderStatus orderStatus; //주문 상태
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public OrderHistoryDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
        this.orderStatus = order.getOrderStatus();
    }
    //주문 상품리스트
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}
