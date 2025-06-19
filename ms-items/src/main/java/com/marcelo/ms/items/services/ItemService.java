package com.marcelo.ms.items.services;

import java.util.List;
import java.util.Optional;

import com.marcelo.ms.items.models.ItemDto;

public interface ItemService {
	
	List<ItemDto> findAll();
	
	Optional<ItemDto> findById(Long id);
	
}
