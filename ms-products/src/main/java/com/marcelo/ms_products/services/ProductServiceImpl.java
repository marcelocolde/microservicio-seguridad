package com.marcelo.ms_products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.ms_products.entities.Product;
import com.marcelo.ms_products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	final private ProductRepository repository;
	
	final Environment environment;
	
	public ProductServiceImpl(ProductRepository repository, Environment environment) {
		this.repository = repository;
		this.environment = environment;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return ((List<Product>) repository.findAll()).stream().map(product -> {
			product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return product;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id) {
		return repository.findById(id).map(product -> {
			product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return product;
		});
	}

}
