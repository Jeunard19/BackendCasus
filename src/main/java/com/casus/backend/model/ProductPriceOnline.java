package com.casus.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductPriceOnline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long productId; 
	private Double priceOnline; 
	private String websiteAddress;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPriceOnline() {
		return priceOnline;
	}
	public void setPriceOnline(Double priceOnline) {
		this.priceOnline = priceOnline;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	} 
	
	
	
	

}
