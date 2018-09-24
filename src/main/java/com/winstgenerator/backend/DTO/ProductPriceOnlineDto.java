package com.winstgenerator.backend.DTO;

public class ProductPriceOnlineDto {
	
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
	

}
