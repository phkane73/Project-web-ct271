package com.ct271.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct271.entity.Product;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long>{
    public Page<Product> findAllByCategoryName(String categoryName, Pageable page);
    public Page<Product> findAllByCategoryNameAndBrand(String categoryName, String brand, Pageable page);

    public long countByIsDeleted(int x);
}
