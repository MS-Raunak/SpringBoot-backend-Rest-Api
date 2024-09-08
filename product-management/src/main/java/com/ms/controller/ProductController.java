package com.ms.controller;

import java.util.List;

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
import com.ms.repository.ProductRepository;
import com.ms.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/save-product")
	public ResponseEntity<?> saveProductMethod(@RequestBody ProductDto productDto) {
		Boolean saveProduct = null;
		//Handle unwanted exception at runtime using traditional(try-catch) way
		try {
			saveProduct = productService.saveProduct(productDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (!saveProduct) {
			return new ResponseEntity<>("product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<>("product saved successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProductsMethod() {
		List<ProductDto> allProductDtos = null;
		try {
			allProductDtos = productService.getAllProduct();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
		
		if (CollectionUtils.isEmpty(allProductDtos)) {
			return new ResponseEntity<>("products not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allProductDtos, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?>getProductMethod(@PathVariable(name = "id") Integer id) {
		ProductDto productDtos = null;
		try {
			productDtos = productService.getProductById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
		
		if (ObjectUtils.isEmpty(productDtos)) {
			return new ResponseEntity<>("products not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}
	
	@PutMapping("update-product/{id}")
	public ResponseEntity<?> putMethodName(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) {
		ProductDto updateProductDto = null;
		try {
			updateProductDto = productService.updateProductByID(productDto, id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (ObjectUtils.isEmpty(updateProductDto)) {
			return new ResponseEntity<>("products not found with id " + id, HttpStatus.NOT_FOUND);
		}
		System.out.println(updateProductDto);
		return new ResponseEntity<>(updateProductDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeProducEntity(@PathVariable("id") Integer id){
		Boolean isProductDeleted = null;
		try {
			isProductDeleted = productService.deleteProductById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(!isProductDeleted) {
			return new ResponseEntity<>("product not found with id " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("product deleted succcessfully", HttpStatus.OK);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleException(){
		return new ResponseEntity<>("Illegal argument exception", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
