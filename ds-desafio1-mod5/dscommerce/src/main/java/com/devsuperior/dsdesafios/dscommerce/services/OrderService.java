package com.devsuperior.dsdesafios.dscommerce.services;

import com.devsuperior.dsdesafios.dscommerce.dto.ClientDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.OrderDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dsdesafios.dscommerce.dto.PaymentDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.*;
import com.devsuperior.dsdesafios.dscommerce.projections.OrderProjection;
import com.devsuperior.dsdesafios.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dsdesafios.dscommerce.repositories.OrderRepository;
import com.devsuperior.dsdesafios.dscommerce.repositories.ProductRepository;
import com.devsuperior.dsdesafios.dscommerce.services.exceptions.ForbiddenException;
import com.devsuperior.dsdesafios.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Transactional(readOnly = true)
    public OrderDTO searchById(Long id) {
        List<OrderProjection> orders = orderRepository.searchById(id);

        Order order = getOrderFrom(orders);

        validateOrderClient(order, userService.getAuthenticatedUser().getId());

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

    private static Order getOrderFrom(List<OrderProjection> orders) {
        OrderProjection orderProjection = orders.getFirst();

        Order order = new Order();

        order.setId(orderProjection.getOrderId());
        order.setMoment(orderProjection.getOrderMoment());
        order.setStatus(orderProjection.getOrderStatus());

        User client = getUserFromProjection(orderProjection);
        order.setClient(client);

        Payment payment = getPaymentFromProjection(orderProjection);
        order.setPayment(payment);

        Set<OrderItem> items = getOrderItemsFromProjection(orders);
        order.getItems().addAll(items);

        return order;
    }

    private static Payment getPaymentFromProjection(OrderProjection orderProjection) {
        Payment payment = new Payment();
        payment.setId(orderProjection.getPaymentId());
        payment.setMoment(orderProjection.getPaymentMoment());
        return payment;
    }

    private static User getUserFromProjection(OrderProjection orderProjection) {
        User client = new User();
        client.setId(orderProjection.getClientId());
        client.setName(orderProjection.getClientName());
        return client;
    }

    private static Set<OrderItem> getOrderItemsFromProjection(List<OrderProjection> orders) {
        Set<OrderItem> items = new HashSet<>();

        orders.forEach(o -> {
            OrderItem item = new OrderItem();

            item.setOrder(getOrderFromProjection(o));
            item.setProduct(getProductFromProjection(o));
            item.setPrice(o.getProductPrice());
            item.setQuantity(o.getQuantity());

            items.add(item);
        });

        return items;
    }

    private static Order getOrderFromProjection(OrderProjection o) {
        Order order = new Order();
        order.setId(o.getOrderId());
        order.setMoment(o.getOrderMoment());
        order.setStatus(o.getOrderStatus());
        return order;
    }

    private static Product getProductFromProjection(OrderProjection o) {
        Product product = new Product();
        product.setId(o.getProductId());
        product.setImgUrl(o.getProductImgUrl());
        product.setName(o.getProductName());
        product.setPrice(o.getProductPrice());
        return product;
    }

    public void validateOrderClient(Order order, Long requestClientId) throws ForbiddenException {
        User user = userService.getAuthenticatedUser();

        if(!user.getId().equals(requestClientId) || ! order.getClient().getId().equals(requestClientId)) {
            throw new ForbiddenException("Acesso negado");
        }
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        User user = userService.getAuthenticatedUser();

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
