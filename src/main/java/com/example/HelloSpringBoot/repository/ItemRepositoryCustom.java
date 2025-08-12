package com.example.HelloSpringBoot.repository;

import com.example.HelloSpringBoot.dto.ItemSearchDto;
import com.example.HelloSpringBoot.dto.MainItemDto;
import com.example.HelloSpringBoot.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
