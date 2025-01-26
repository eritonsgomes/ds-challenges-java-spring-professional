package com.devsuperior.dsdesafios.dsdesafio1mod1.service;

import com.devsuperior.dsdesafios.dsdesafio1mod1.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ShippingService shippingService;

    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public double total(Order order) {
        var shipment = shippingService.shipment(order);
        var orderTotal = order.getBasic() - calculateDiscount(order.getBasic(), order.getDiscount());

        return orderTotal + shipment;
    }

    private double calculateDiscount(Double value, Double percent) {
        return  percent * value / 100;
    }

}
