package com.casus.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casus.backend.dto.ProductDto;
import com.casus.backend.model.Product;
import com.casus.backend.service.IProductService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {
	
	@Autowired
	private IProductService iProductService; 
	
	@GetMapping("/api/product/{id}")
	public ProductDto findById(@PathVariable Long id) {
		
		Optional<Product> optional = this.iProductService.findOne(id);

		if (optional.isPresent()) {
			ProductDto productDto = new ProductDto(); 
			productDto.setId(optional.get().getId());
			productDto.setProductName(optional.get().getProductName());
			productDto.setProductCategory(optional.get().getProductCategory());
			
			
			return productDto;
		}
		return null;
	}
	
	
	
	@PostMapping("/api/product")
	public Product create(@RequestBody Product product) {
		
		return this.iProductService.create(product);
	}
		
	
}
