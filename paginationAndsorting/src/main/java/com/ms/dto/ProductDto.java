package com.ms.dto;

import lombok.Data;

@Data
public class ProductDto {
	private Integer id;
	private String productName;
	private String description;
	private Double price;
}
