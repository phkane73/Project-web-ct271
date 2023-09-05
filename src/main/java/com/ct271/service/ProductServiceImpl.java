package com.ct271.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ct271.entity.Product;
import com.ct271.repository.IProductRepo;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private IProductRepo iProductRepo;
	
	@Override
	public Product addProduct(Product product) {
		return iProductRepo.save(product);
	}

	@Override
	public Optional<Product> getProduct(Long id) {
		return iProductRepo.findById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return iProductRepo.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return iProductRepo.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return iProductRepo.findAll(sort);
	}

	@Override
	public long getTotalElement() {
		return iProductRepo.count() ;
	}
	
	

}
