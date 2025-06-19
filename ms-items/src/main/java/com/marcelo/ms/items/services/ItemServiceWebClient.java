package com.marcelo.ms.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.marcelo.ms.items.models.ItemDto;
import com.marcelo.ms.items.models.ProductDto;

//@Primary
@Service
public class ItemServiceWebClient implements ItemService{

	private final WebClient.Builder client;
	
	public ItemServiceWebClient(Builder client) {
		this.client = client;
	}

	@Override
	public List<ItemDto> findAll() {
		return this.client
				.build()
				.get()
//				.uri("http://ms-products") // se puede agregar como una ruta base en WebClientConfig
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(ProductDto.class)
				.map(product -> new ItemDto(product, new Random().nextInt(10) + 1))
				.collectList()
				.block(); // no reactiva
		
	}

	@Override
	public Optional<ItemDto> findById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		try {
			return client
					.build() 
					.get()
					.uri("http://ms-products/{id}",params) 
					.accept(MediaType.APPLICATION_JSON) // aceptara datos de tipo JSON
					.retrieve() // intercambiar datos
					.bodyToMono(ProductDto.class)
					.map(product -> new ItemDto(product, new Random().nextInt(10) + 1))
					.blockOptional();
		}catch(WebClientResponseException e) {
			return Optional.empty();
		}
		
	}

}
