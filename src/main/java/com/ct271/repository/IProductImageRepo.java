package com.ct271.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct271.entity.ProductImage;

@Repository
public interface IProductImageRepo extends JpaRepository<ProductImage, Integer>{
	
}
