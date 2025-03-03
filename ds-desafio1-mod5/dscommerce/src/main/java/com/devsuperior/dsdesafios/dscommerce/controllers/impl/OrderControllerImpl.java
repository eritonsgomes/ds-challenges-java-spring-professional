package com.devsuperior.dsdesafios.dscommerce.controllers.impl;

import com.devsuperior.dsdesafios.dscommerce.controllers.OrderController;
import com.devsuperior.dsdesafios.dscommerce.dto.OrderDTO;
import com.devsuperior.dsdesafios.dscommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO dto = orderService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
        dto = orderService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
