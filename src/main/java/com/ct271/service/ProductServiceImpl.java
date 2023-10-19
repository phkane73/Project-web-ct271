package com.ct271.service;

import com.ct271.DeleteFile;
import com.ct271.entity.Product;
import com.ct271.repository.IConfigureRepo;
import com.ct271.repository.IProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

	private final IProductRepo iProductRepo;

	private final IConfigureRepo iConfigureRepo;

	public ProductServiceImpl(IProductRepo iProductRepo, IConfigureRepo iConfigureRepo) {
		this.iProductRepo = iProductRepo;
		this.iConfigureRepo = iConfigureRepo;
	}

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
	public Page<Product> findAllCategory(String categoryName, Pageable pageable) {
		return iProductRepo.findAllByCategoryName(categoryName, pageable);
	}

	@Override
	public Page<Product> findAllCategoryAndBrand(String categoryName, String brand, Pageable pageable) {
		return iProductRepo.findAllByCategoryNameAndBrand(categoryName, brand, pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return iProductRepo.findAll(sort);
	}

	@Override
	public long getTotalElement() {
		return iProductRepo.count();
	}

	@Override
	public boolean deleteProduct(Long id) {
		Optional<Product> product = iProductRepo.findById(id);
		if (product != null) {
			String pathImageInforProductBE = "./src/main/resources/static/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductBE);
			String pathImageInforProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductFE);
			String pathImageProductBE = "./src/main/resources/static/images/" + id;
			DeleteFile.deleteFile(pathImageProductBE);
			String pathImageProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id;
			DeleteFile.deleteFile(pathImageProductFE);
			iProductRepo.deleteById(id);
			iConfigureRepo.deleteById(product.get().getConfigure().getId());
			return true;
		}
		return false;
	}

	@Override
	public Product updateProduct(Long id, Product newProduct) {
		Optional<Product> product = iProductRepo.findById(id);
		if (product != null) {
			if (newProduct.getBrand() != null) {
				product.get().setBrand(newProduct.getBrand());
			}
			if (newProduct.getCategoryName() != null) {
				product.get().setCategoryName(newProduct.getCategoryName());
			}
			if (newProduct.getImageProductInfor() != null) {
				product.get().setImageProductInfor(newProduct.getImageProductInfor());
			}
			if (newProduct.getNumber() != 0) {
				product.get().setNumber(newProduct.getNumber());
			}
			if (newProduct.getPrice() != 0) {
				product.get().setPrice(newProduct.getPrice());
			}
			if (newProduct.getProductName() != null) {
				product.get().setProductName(newProduct.getProductName());
			}
			return iProductRepo.save(product.get());
		}
		return null;
	}
}
