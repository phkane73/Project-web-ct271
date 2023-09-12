package com.ct271.service;

import com.ct271.entity.Configure;
import com.ct271.entity.Product;

public interface IConfigureService {
	
	//Thêm một cấu hình mới
	public Configure addConfigure(Product product);
	
	//Chỉnh sửa cấu hình của sản phẩm
	public Configure updateConfigure(Long id,Product product);
}
