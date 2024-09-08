package com.ms.service;

import java.util.List;

import com.ms.dto.ProductDto;
import com.ms.pagesinfo.ProductPageInfo;

public interface ProductService {
	public Boolean saveProduct(ProductDto productDto);
	public List<ProductDto> getAllProduct();
	
	//Pagenation: Basic Pagination
	//public List<ProductDto> getProductsPagination(Integer pageNumber, Integer pageSize);
	//Advance Pagination
	public ProductPageInfo getProductsPagination(Integer pageNumber, Integer pageSize);
	
	//Pagination with Sorting
	public ProductPageInfo getProductsPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy,String sortOrder);
}
