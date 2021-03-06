package com.casus.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String productName; 
	


	private String productCategory; 
	
	private Double pricePaid; 

	
	private Double pricesOnline; 
	
	public Double getPricesOnline() {
		return pricesOnline;
	}

	public void setPricesOnline(Double pricesOnline) {
		this.pricesOnline = pricesOnline;
	}

	public void setWinstMargin(Double winstMargin) {
		this.winstMargin = winstMargin;
	}

	private Double winstMargin;
	
	private Double Margin;

	public Double getMargin() {
		return Margin;
	}

	public void setMargin(Double margin) {
		Margin = margin;
	}

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

	public Double getWinstMargin() {
		return winstMargin;
	}

	public void setWinstMargin(double winstMargin) {
		this.winstMargin = winstMargin;
	}

	


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
