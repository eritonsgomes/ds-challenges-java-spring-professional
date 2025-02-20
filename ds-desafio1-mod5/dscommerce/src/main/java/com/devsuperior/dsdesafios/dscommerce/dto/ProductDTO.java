package com.devsuperior.dsdesafios.dscommerce.dto;

public class ProductDTO {

	private Long id;
	private String name;
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ProductDTO(com.devsuperior.dsdesafios.dscommerce.entities.Product entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
