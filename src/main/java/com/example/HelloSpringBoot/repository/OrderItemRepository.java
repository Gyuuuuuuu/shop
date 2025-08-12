package com.example.HelloSpringBoot.repository;

import com.example.HelloSpringBoot.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
