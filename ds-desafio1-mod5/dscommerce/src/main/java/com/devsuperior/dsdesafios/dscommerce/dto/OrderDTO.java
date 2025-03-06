package com.devsuperior.dsdesafios.dscommerce.dto;

import com.devsuperior.dsdesafios.dscommerce.entities.Order;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderItem;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um produto")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
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
