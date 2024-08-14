package com.efuture.assignment.dto;

import java.util.List;

public class ResponseDTO {

	private int code;
	private String message;
	private List<ValidationError> errors;
	private List<ProductDto> products;
	private List<ProductDtoResponse> productAll;
	private ProductDto product;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public List<ProductDtoResponse> getProductAll() {
		return productAll;
	}
	public void setProductAll(List<ProductDtoResponse> productAll) {
		this.productAll = productAll;
	}
	public List<ValidationError> getErrors() {
		return errors;
	}
	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	} 
	
	
}
