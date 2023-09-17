package com.ct271.controller;

import com.ct271.entity.Product;
import com.ct271.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final IProductService iProductService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct(){
        List<Product> products = iProductService.getAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
