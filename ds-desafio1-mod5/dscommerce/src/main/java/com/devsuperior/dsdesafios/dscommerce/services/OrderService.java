package com.devsuperior.dsdesafios.dscommerce.services;

import com.devsuperior.dsdesafios.dscommerce.dto.ClientDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.OrderDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.PaymentDTO;
import com.devsuperior.dsdesafios.dscommerce.projections.OrderProjection;
import com.devsuperior.dsdesafios.dscommerce.repositories.OrderRepository;
import com.devsuperior.dsdesafios.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        List<OrderProjection> orders = orderRepository.searchById(id);

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        return getOrderDTOFrom(orders);
    }

    private static OrderDTO getOrderDTOFrom(List<OrderProjection> orders) {
        OrderProjection order = orders.getFirst();

        ClientDTO clientDTO = new ClientDTO(order.getClientId(), order.getClientName());
        PaymentDTO paymentDTO = new PaymentDTO(order.getPaymentId(), order.getPaymentMoment());
        List<OrderItemDTO> items = new ArrayList<>();

        orders.forEach(o -> {
            OrderItemDTO itemDTO = new OrderItemDTO(o.getProductId(), o.getProductName(), o.getProductPrice(),
                    o.getQuantity(), o.getProductImgUrl());
            items.add(itemDTO);
        });

        OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getOrderMoment(),
                order.getOrderStatus(), clientDTO, paymentDTO);

        orderDTO.getItems().addAll(items);

        return orderDTO;
    }

}
