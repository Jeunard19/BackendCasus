package com.casus.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String productName; 
	
	private String productCategory; 
	
	private Double pricePaid; 
	
	//@OneToMany
	//public List<Double> pricesOnline; 
	
	private Integer winstMargin;

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(Double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public Integer getWinstMargin() {
		return winstMargin;
	}

	public void setWinstMargin(Integer winstMargin) {
		this.winstMargin = winstMargin;
	}

	//public List<Double> getPricesOnline() {
	//	return pricesOnline;
	//}

	//public void setPricesOnline(List<Double> pricesOnline) {
	//	this.pricesOnline = pricesOnline;
	//}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	} 
	
	
	
	

}
