package com.marcelo.ms.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.marcelo.ms.items.clients.ProductFeignClient;
import com.marcelo.ms.items.models.ItemDto;
import com.marcelo.ms.items.models.ProductDto;

import feign.FeignException;

@Primary
@Service
public class ItemServiceFeign implements ItemService{

	@Autowired // tambien se puede inyectar via constructor
	private ProductFeignClient client;
	
	@Override
	public List<ItemDto> findAll() {
		return client.findAll()
				.stream()
				.map(product -> new ItemDto(product, new Random().nextInt(10) + 1 ))  // sumo 1 para evitar el cero
		.collect(Collectors.toList());	
	}

	@Override
	public Optional<ItemDto> findById(Long id) {
		try {
			ProductDto product = client.detail(id);
			return Optional.of(new ItemDto(product, new Random().nextInt(10) + 1 ));
		} catch (FeignException e) {
			return Optional.empty();			
		}
		
	}

}
