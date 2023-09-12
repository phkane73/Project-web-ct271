package com.ct271.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;
import com.ct271.repository.IProductImageRepo;

@Service
public class ProductImageServiceImpl implements IProductImageService{

	@Autowired
	private IProductImageRepo iProductImageRepo;
	
	@Override
	public ProductImage addImage(ProductImage productImage) {
		return iProductImageRepo.save(productImage);
	}

	@Override
	public List<ProductImage> getImages() {
		return iProductImageRepo.findAll();
	}

	@Override
	public boolean deleteImage(Product product) {
		List<ProductImage> images = iProductImageRepo.findImagesByProductId(product.getId());
		iProductImageRepo.deleteAll(images);;
		return false;
	}
	
}
