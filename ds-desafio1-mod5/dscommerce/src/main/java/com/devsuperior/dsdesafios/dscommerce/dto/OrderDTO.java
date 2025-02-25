package com.devsuperior.dsdesafios.dscommerce.dto;

import com.devsuperior.dsdesafios.dscommerce.entities.Order;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderItem;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class OrderDTO {

    private final Long id;
    private final Instant moment;
    private final OrderStatus status;
    private final ClientDTO client;
    private final PaymentDTO payment;
    private final List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new ClientDTO(entity.getClient());
        this.payment = isNull(entity.getPayment()) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item: entity.getItems()) {
            items.add(new OrderItemDTO(item));
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(new OrderItemDTO(item));
    }

    public Double getTotal() {
        Double sum = 0.0;

        for (OrderItemDTO item: items) {
            sum+= item.getSubTotal();
        }

        return sum;
    }

}
