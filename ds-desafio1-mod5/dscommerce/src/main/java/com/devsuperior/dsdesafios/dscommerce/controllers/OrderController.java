package com.devsuperior.dsdesafios.dscommerce.controllers;

import com.devsuperior.dsdesafios.dscommerce.dto.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderController {

    @GetMapping(value = "/{id}")
    ResponseEntity<OrderDTO> findById(@PathVariable Long id);

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto);

}
