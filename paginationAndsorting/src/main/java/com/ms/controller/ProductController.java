package com.ms.controller;

import java.util.List;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dto.ProductDto;
import com.ms.pagesinfo.ProductPageInfo;
import com.ms.repository.ProductRepository;
import com.ms.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/save-product")
	public ResponseEntity<?> saveProductMethod(@RequestBody ProductDto productDto) {
		Boolean saveProduct = productService.saveProduct(productDto);
		
		if (!saveProduct) {
			return new ResponseEntity<>("product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("product saved successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProductsMethod() {
		List<ProductDto> allProductDtos = productService.getAllProduct();
		
		if (CollectionUtils.isEmpty(allProductDtos)) {
			return new ResponseEntity<>("products not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allProductDtos, HttpStatus.OK);
	}
	
	//Pagination: Basic Pagination
	/*@GetMapping("/all-products")
	public ResponseEntity<?> getMethodName(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			                    @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
		List<ProductDto> productsPagination = productService.getProductsPagination(pageNumber, pageSize);
		return new ResponseEntity<>(productsPagination, HttpStatus.OK);
	}*/
	
	//Advance Pagination
	@GetMapping("/all-products")
	public ResponseEntity<ProductPageInfo> getMethodName(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			                    @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
		ProductPageInfo productsPages = productService.getProductsPagination(pageNumber, pageSize);
		return new ResponseEntity<ProductPageInfo>(productsPages, HttpStatus.OK);
	}
	
	//Pagination with Sorting: default sort by id(we can sort any other attribute by passing in url)
	@GetMapping("/allProducts")
	public ResponseEntity<ProductPageInfo> getPaginationAndSorting(
								@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			                    @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
			                    @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
			                    @RequestParam(value = "sortOrder") String sortOrder) {
		
		ProductPageInfo productsPages = productService.getProductsPaginationAndSorting(pageNumber, pageSize, sortBy, sortOrder);
		
		return new ResponseEntity<ProductPageInfo>(productsPages, HttpStatus.OK);
	}
	
	
}
