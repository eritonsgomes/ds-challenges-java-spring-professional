package com.devsuperior.dsdesafios.dscommerce.controllers;

import com.devsuperior.dsdesafios.dscommerce.dto.CategoryMinDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CategoryController {

    @GetMapping
    ResponseEntity<List<CategoryMinDTO>> findAll();

}
