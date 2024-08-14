package com.efuture.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "product_category")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int productCatgeoryId;
	@Column(length=100)
	private String name;
	@Column(length=500)
	private String description;
	
	public int getProductCatgeoryId() {
		return productCatgeoryId;
	}
	public void setProductCatgeoryId(int productCatgeoryId) {
		this.productCatgeoryId = productCatgeoryId;
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
	
	
	
}
