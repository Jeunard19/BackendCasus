package com.casus.backend.service;

import java.util.List;

import com.casus.backend.model.ProductPriceOnline;



//import java.util.List;



public interface IProductPriceOnlineService {
	
	//public List<ProductPriceOnline> findAll(); 
	
	public ProductPriceOnline create(ProductPriceOnline product);

	public List<ProductPriceOnline> findByProductId(Long productId);
	
	//meer methodes? 
}
