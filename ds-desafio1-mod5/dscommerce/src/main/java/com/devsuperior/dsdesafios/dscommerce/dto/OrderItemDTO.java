package com.devsuperior.dsdesafios.dscommerce.dto;

import com.devsuperior.dsdesafios.dscommerce.entities.OrderItem;

public class OrderItemDTO {

    private final Long productId;
    private final String name;
    private final Double price;
    private final Integer quantity;
    private final String imgUrl;

    public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDTO(OrderItem entity) {
        this.productId = entity.getProduct().getId();
        this.imgUrl = entity.getProduct().getImgUrl();
        this.name = entity.getProduct().getName();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getSubTotal() {
        return quantity * price;
    }

}
