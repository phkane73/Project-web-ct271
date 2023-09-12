package com.ct271.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ct271.entity.ProductImage;

@Repository
public interface IProductImageRepo extends JpaRepository<ProductImage, Integer>{
	
	@Query(value = "SELECT * FROM product_image WHERE product_id = ?1", nativeQuery = true)
	List<ProductImage> findImagesByProductId(Long id);
	
}
