package com.devsuperior.dsdesafios.dscommerce.services;

import com.devsuperior.dsdesafios.dscommerce.dto.CategoryMinDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.Category;
import com.devsuperior.dsdesafios.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryMinDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMinDTO::new).toList();
    }

}
