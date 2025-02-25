package com.devsuperior.dsdesafios.dscommerce.services;

import com.devsuperior.dsdesafios.dscommerce.dto.*;
import com.devsuperior.dsdesafios.dscommerce.entities.*;
import com.devsuperior.dsdesafios.dscommerce.projections.OrderProjection;
import com.devsuperior.dsdesafios.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dsdesafios.dscommerce.repositories.OrderRepository;
import com.devsuperior.dsdesafios.dscommerce.repositories.ProductRepository;
import com.devsuperior.dsdesafios.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

    // TODO: Permitir apenas acesso aos Clientes
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        // TODO: Incluir o Cliente Autenticado no Pedido
        User user = new User();
        user.setId(dto.getClient().getId());
        user.setName(dto.getClient().getName());

        Order order = new Order();
        order.setClient(user);
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        for(OrderItemDTO itemDTO: dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

}
