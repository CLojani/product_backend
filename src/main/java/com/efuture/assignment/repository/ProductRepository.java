package com.efuture.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.efuture.assignment.model.Product;
import com.efuture.assignment.model.ProductCategory;

public interface ProductRepository extends JpaRepository<Product,Long>{

	Product findByProductId(int productId);
	
	List<Product> findByProductCategory(ProductCategory productCategory);

	@Query(value = "select * from product where price >= 500", nativeQuery = true)		List<Product> getProductsByPrice();
}
