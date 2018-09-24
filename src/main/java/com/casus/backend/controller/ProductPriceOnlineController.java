package com.casus.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casus.backend.model.ProductPriceOnline;
import com.casus.backend.service.IProductPriceOnlineService;

@RestController
public class ProductPriceOnlineController {
	
	@Autowired
	private IProductPriceOnlineService iProductPriceOnlineService; 
	
	@GetMapping("/api/productpriceonline/product/{id}")
	public List<ProductPriceOnline> findByProductId(@PathVariable("id") Long productId) {
		return this.iProductPriceOnlineService.findByProductId(productId);
	}
	
	@PostMapping("/api/productpriceonline")
	public ProductPriceOnline create(@RequestBody ProductPriceOnline productPriceOnline) {
		return this.iProductPriceOnlineService.create(productPriceOnline);
	}

}
