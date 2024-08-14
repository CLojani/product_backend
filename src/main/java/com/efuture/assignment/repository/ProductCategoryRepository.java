package com.efuture.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efuture.assignment.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{

	ProductCategory findByName(String category);

}
