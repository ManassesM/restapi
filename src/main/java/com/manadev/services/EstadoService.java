package com.manadev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manadev.domain.Estado;
import com.manadev.dto.EstadoDTO;
import com.manadev.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	// aux 
	
	public Estado fromDto(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getNome());
	}
}
