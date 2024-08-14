package com.efuture.assignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "product_comment")
public class ProductComment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int productCommentId;
	@Column(length=300)
	private String comment;
	@ManyToOne
	private Product product;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt; 
	
	public int getProductCommentId() {
		return productCommentId;
	}
	public void setProductCommentId(int productCommentId) {
		this.productCommentId = productCommentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
