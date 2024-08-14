package com.efuture.assignment.dto;

import java.math.BigDecimal;
import java.util.List;

import com.efuture.assignment.model.ProductCategory;
import com.efuture.assignment.model.ProductComment;

public class ProductDtoResponse {

	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private ProductCategory category;
	private String status;
	private List<ProductComment> comments;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ProductComment> getComments() {
		return comments;
	}
	public void setComments(List<ProductComment> comments) {
		this.comments = comments;
	}
	
	
}
