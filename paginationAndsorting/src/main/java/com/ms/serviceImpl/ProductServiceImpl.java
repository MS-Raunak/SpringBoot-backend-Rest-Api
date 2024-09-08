package com.ms.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.ms.dto.ProductDto;
import com.ms.model.Product;
import com.ms.pagesinfo.ProductPageInfo;
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

	
	//Pagination service
	/*@Override
	public List<ProductDto> getProductsPagination(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> allPages = productRepository.findAll(pageable);
		List<Product> productContent = allPages.getContent();
		
		//copy products into product dto
		List<ProductDto> productDtos = productContent.stream().map(product->mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		return productDtos;
	}*/
	
	//Advance Pagination
	@Override
	public ProductPageInfo getProductsPagination(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> allPages = productRepository.findAll(pageable);
		List<Product> productContent = allPages.getContent();
		
		//copy products into product dto
		List<ProductDto> productDtos = productContent.stream().map(product->mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductPageInfo productPageInfo = new ProductPageInfo();
		
		productPageInfo.setProducts(productDtos);
		productPageInfo.setPageNumber(allPages.getNumber());
		productPageInfo.setPageSize(allPages.getSize());
		productPageInfo.setTotalElements(allPages.getTotalElements());
		productPageInfo.setTotalPages(allPages.getTotalPages());
		productPageInfo.setLastPage(allPages.isLast());
		
		
		return productPageInfo;
	}
	
	
	//Pagination With Sorting
	@Override
	public ProductPageInfo getProductsPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sort = null;
		
		if (sortOrder.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		//Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));//by default asc
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort); //dynamic order based on user request
		
		Page<Product> allPages = productRepository.findAll(pageable);
		List<Product> productContent = allPages.getContent();
		
		//copy products into product dto
		List<ProductDto> productDtos = productContent.stream().map(product->mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductPageInfo productPageInfo = new ProductPageInfo();
		
		productPageInfo.setProducts(productDtos);
		productPageInfo.setPageNumber(allPages.getNumber());
		productPageInfo.setPageSize(allPages.getSize());
		productPageInfo.setTotalElements(allPages.getTotalElements());
		productPageInfo.setTotalPages(allPages.getTotalPages());
		productPageInfo.setLastPage(allPages.isLast());
		
		
		return productPageInfo;
	}

}
