package com.example.springboot.service;

import com.example.springboot.model.Product;
import com.example.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Page<Product> getAllProduct(int page , int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAll(pageable);
    }
}
