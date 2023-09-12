package com.ct271.service;

import java.util.List;

import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;

public interface IProductImageService {

	//Thêm ảnh sản phẩm
	public ProductImage addImage(ProductImage productImage);
	
	//Lấy ra tất cả ảnh
	public List<ProductImage> getImages();
	
	//Xóa ảnh
	public boolean deleteImage(Product product);

}
