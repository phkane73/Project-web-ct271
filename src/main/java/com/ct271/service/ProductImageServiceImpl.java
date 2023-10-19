package com.ct271.service;

import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;
import com.ct271.repository.IProductImageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements IProductImageService {

	private final IProductImageRepo iProductImageRepo;

	public ProductImageServiceImpl(IProductImageRepo iProductImageRepo) {
		this.iProductImageRepo = iProductImageRepo;
	}

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
		iProductImageRepo.deleteAll(images);
		;
		return false;
	}

}
