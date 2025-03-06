package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.OrderDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.Order;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.OrderItemMapperQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = { ClientMapper.class, OrderItemCollectionMapper.class, PaymentMapper.class })
public interface OrderMapper extends BeanMapper<Order, OrderDTO> {

    @Override
    @Mapping(target = "items", qualifiedBy = { OrderItemMapperQualifier.class })
    OrderDTO toDTO(Order order);

    @Override
    @Mapping(target = "items", qualifiedBy = { OrderItemMapperQualifier.class })
    Order toEntity(OrderDTO orderDTO);

    @Override
    @Mapping(target = "items", qualifiedBy = { OrderItemMapperQualifier.class })
    List<OrderDTO> toDTO(List<Order> orders);

    @Override
    @Mapping(target = "items", qualifiedBy = { OrderItemMapperQualifier.class })
    List<Order> toEntity(List<OrderDTO> orderDTOS);

}
