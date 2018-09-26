package com.casus.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.casus.backend.model.Product;



public interface IProductDao extends CrudRepository<Product, Long > {
	
	@Override
	public List<Product> findAll();

	public Optional<Product> findByUserId(Long userId);
	
	//bv hier moeilijkere methodes. Alle producten die vorige week zijn toegevoegd

	
	
	//public List<Product> findbyCategory(String categoryName); 
	
	//@Query("SELECT p FROM Product p WHERE p.price < :price")
	//public List<Product> findUnderCertainPrice(@Param("price") double priceArgs);
	
	

}
