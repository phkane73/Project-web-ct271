package com.ct271.service;

import java.util.List;
import java.util.Optional;

import com.ct271.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ct271.entity.Product;

public interface IProductService {

	//Thêm một sản phẩm mới
	public Product addProduct(Product product);
	
	//Xóa sản phẩm
	public boolean deleteProduct(Long id);
	
	//Lấy ra 1 sản phẩm
	public Optional<Product> getProduct(Long id);
	
	//Lấy ra tất cả sản phẩm
	public List<Product> getAllProducts();
	
	//Phân trang
	public Page<Product> findAll(Pageable pageable);

	//Tìm các product theo loại
	public Page<Product> findAllCategory(String categoryName,Pageable pageable);
	//Tìm các product theo loại và hãng
	public Page<Product> findAllCategoryAndBrand(String categoryName,String brand,Pageable pageable);
	//Sort
	public List<Product> findAll(Sort sort);
	
	//Lấy ra số product
	public long getTotalElement();
	
	//Chỉnh sửa sản phẩm
	public Product updateProduct(Long id, Product newProduct);

}
