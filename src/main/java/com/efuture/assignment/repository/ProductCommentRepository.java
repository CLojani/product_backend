package com.efuture.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efuture.assignment.model.Product;
import com.efuture.assignment.model.ProductComment;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long>{

	List<ProductComment> findByProduct(Product p);

}
