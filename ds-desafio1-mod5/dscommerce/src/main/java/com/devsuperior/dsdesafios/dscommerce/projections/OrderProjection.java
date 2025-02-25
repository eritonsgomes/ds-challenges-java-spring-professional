package com.devsuperior.dsdesafios.dscommerce.projections;

import com.devsuperior.dsdesafios.dscommerce.entities.OrderStatus;

import java.time.Instant;

public interface OrderProjection {

    Long getOrderId();
    Instant getOrderMoment();
    OrderStatus getOrderStatus();
    Long getClientId();
    String getClientName();
    Long getProductId();
    String getProductImgUrl();
    String getProductName();
    Double getProductPrice();
    Integer getQuantity();
    Long getPaymentId();
    Instant getPaymentMoment();

}
