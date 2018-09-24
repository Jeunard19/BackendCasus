package com.casus.backend.dto;

public class ProductDto {
	
	private Long id;
	
	private String productName; 
	
	private String productCategory; 
	
	private Double pricePaid; 
	
	//public List<Double> pricesOnline; 
	
	private Integer winstMargin;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	//public List<Double> getPricesOnline() {
	//	return pricesOnline;
	//}

	//public void setPricesOnline(List<Double> pricesOnline) {
	//	this.pricesOnline = pricesOnline;
	//}

	public Integer getWinstMargin() {
		return winstMargin;
	}

	public void setWinstMargin(Integer winstMargin) {
		this.winstMargin = winstMargin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
