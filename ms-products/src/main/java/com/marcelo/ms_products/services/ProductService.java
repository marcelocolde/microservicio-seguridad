package com.marcelo.ms_products.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.ms_products.entities.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public Optional<Product> findById(Long id);

}
