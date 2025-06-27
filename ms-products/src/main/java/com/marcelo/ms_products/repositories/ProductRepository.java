package com.marcelo.ms_products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.ms_products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

		
}
