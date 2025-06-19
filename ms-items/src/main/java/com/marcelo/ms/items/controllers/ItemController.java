package com.marcelo.ms.items.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ms.items.models.ItemDto;
import com.marcelo.ms.items.services.ItemService;

@RestController
public class ItemController {

	//inyectando dependencia via constructor
	
	private final ItemService service;
	
	public ItemController(ItemService service) {
		this.service = service;
	}

	@GetMapping
	public List<ItemDto> list(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detail(@PathVariable Long id) {
		Optional<ItemDto> itemOptional = service.findById(id);
		if(itemOptional.isPresent()) {
			return ResponseEntity.ok(itemOptional.get());
		}
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("message", "error, no se encuentra el producto");
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap
				("message", "error, no se encuentra el producto"));
	}
	
}
