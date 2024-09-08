package com.ms.pagesinfo;

import java.util.List;

import com.ms.dto.ProductDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductPageInfo {
	private List<ProductDto> products;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;
}
