package com.ms.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.ms.dto.ProductDto;
import com.ms.model.Product;
import com.ms.repository.ProductRepository;
import com.ms.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Boolean saveProduct(ProductDto productDto) {
		//Without model mapper
		/*Product product = new Product();
		product.setId(productDto.getId());
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());	
		productRepository.save(product);*/
		
		Product product = mapper.map(productDto, Product.class);
		Product saveProduct = productRepository.save(product);
		
		if (ObjectUtils.isEmpty(saveProduct)) {
			return false;
		}
		return true;
	}

	@Override
	public List<ProductDto> getAllProduct() {

		List<Product> products = productRepository.findAll();
		List<ProductDto> allProductDtos = products.stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		if (CollectionUtils.isEmpty(allProductDtos)) {
			return null;
		}
		return allProductDtos;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Optional<Product> productOptional = productRepository.findById(id);
		
		if (ObjectUtils.isEmpty(productOptional)) {
			return null;
		}
		
		Product product = productOptional.get();
		ProductDto productDto = mapper.map(product, ProductDto.class);
		
		return productDto;
	}

	@Override
	public ProductDto updateProductByID(ProductDto productDto, Integer id) {
		Optional<Product> productOptional = productRepository.findById(id);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			mapper.map(productDto, product);
			
			Product saveProduct = productRepository.save(product);
			ProductDto updateProductDto = mapper.map(saveProduct, ProductDto.class);
			return updateProductDto;
		}
		
		
		
		return null;
	}

	@Override
	public Boolean deleteProductById(Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
