package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.ProductDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BeanMapper<Product, ProductDTO> {
    
}
