package com.marcelo.ms_products.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ms_products.entities.Product;
import com.marcelo.ms_products.services.ProductService;

@RestController
public class ProductController {
	
	final private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> detail(@PathVariable Long id) {
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.OK).body(productOptional.orElseThrow());
		}		
		return ResponseEntity.notFound().build();
	}
}
