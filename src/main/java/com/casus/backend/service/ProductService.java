package com.casus.backend.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.casus.backend.dao.IProductDao;
import com.casus.backend.model.Product;
import com.casus.backend.scrapper.GoogleResults;




@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductDao iProductDao; 

	@Override
	public List<Product> findAll() {
		return this.iProductDao.findAll(); 
	}

	//@Override
	//public List<Product> findbyCategory(String categoryName) {
	//	return this.iProductDao.findbyCategory(categoryName); 
	//}
	
	@Override
	public Optional<Product> findOne(Long id) {
		if (id < 0)
			return Optional.empty();

		return this.iProductDao.findById(id);
	}

	@Override
	public Product create(Product product,String term) {
		

			Assert.notNull(product,"Product may not be null");
			try {
				GoogleResults re = new GoogleResults(term);
				product.setProductCategory(re.getCator());
				product.setPricePaid(re.getLowestprice());
				product.setWinstMargin(re.getHighestprice()-re.getLowestprice());
			} catch(Exception e) {
				
			}
			
			return this.iProductDao.save(product);
		}
	
	public int getlowestprice(ArrayList<String> listt) {
		
		return 0;
		
	}

	
	
}
