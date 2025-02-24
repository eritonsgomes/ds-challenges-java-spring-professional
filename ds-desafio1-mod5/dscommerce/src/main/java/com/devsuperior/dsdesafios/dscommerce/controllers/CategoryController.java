package com.devsuperior.dsdesafios.dscommerce.controllers;

import com.devsuperior.dsdesafios.dscommerce.dto.CategoryMinDTO;
import com.devsuperior.dsdesafios.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryMinDTO>> findAll() {
        List<CategoryMinDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}
