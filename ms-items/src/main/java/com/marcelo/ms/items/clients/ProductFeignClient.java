package com.marcelo.ms.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marcelo.ms.items.models.ProductDto;

//@FeignClient(url = "localhost:8005", name = "ms-products")
@FeignClient(name = "ms-products") // cuando se implemente eureka no haria falta la url de la api a consumir, sino solo el nombre el microservicio
public interface ProductFeignClient {

	@GetMapping
	public List<ProductDto> findAll();
	
	@GetMapping("/{id}")
	public ProductDto detail(@PathVariable Long id);
	
}
