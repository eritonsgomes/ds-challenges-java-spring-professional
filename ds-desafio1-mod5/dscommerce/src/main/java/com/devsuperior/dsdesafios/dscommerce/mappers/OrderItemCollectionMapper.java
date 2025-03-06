package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderItem;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.OrderItemDtosToOrderItemsQualifier;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.OrderItemMapperQualifier;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.OrderItemToOrderItemDtosQualifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@OrderItemMapperQualifier
public class OrderItemCollectionMapper {

    @OrderItemDtosToOrderItemsQualifier
    static List<OrderItemDTO> mapOrderItemsToOrderItemDtos(Set<OrderItem> entities) {
        List<OrderItemDTO> dtos = new ArrayList<>();

        for (OrderItem item: entities) {
            dtos.add(new OrderItemDTO(item));
        }

        return dtos;
    }

    @OrderItemToOrderItemDtosQualifier
    static Set<OrderItem> mapOrderItemDtosToOrderItems(List<OrderItemDTO> dtos) {
        Set<OrderItem> entities = new HashSet<>();

        for (OrderItemDTO dto: dtos) {
            entities.add(new OrderItem(dto));
        }

        return entities;
    }

}
