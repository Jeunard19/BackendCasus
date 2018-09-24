package com.casus.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casus.backend.model.ProductPriceOnline;



public interface IProductPriceOnlineDao  extends CrudRepository<ProductPriceOnline, Long > {
	
	@Override
	public List<ProductPriceOnline> findAll();

	public List<ProductPriceOnline> findByProductId(Long productId); //Spring snapt zelf dat ie op PI moet zoeken
	
}
