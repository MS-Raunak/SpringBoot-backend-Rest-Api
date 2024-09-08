package com.ms.service;

import java.util.List;

import com.ms.dto.ProductDto;

public interface ProductService {
	public Boolean saveProduct(ProductDto productDto);
	public List<ProductDto> getAllProduct();
	public ProductDto getProductById(Integer id);
	public ProductDto updateProductByID(ProductDto productDto, Integer id);
	public Boolean deleteProductById(Integer id);
}
