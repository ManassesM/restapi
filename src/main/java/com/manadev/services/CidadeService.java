package com.manadev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manadev.domain.Cidade;
import com.manadev.dto.CidadeDTO;
import com.manadev.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
	
	// aux
	
	public Cidade fromDto(CidadeDTO objDto) {
		return new Cidade(objDto.getId(), objDto.getNome(), null);
	}
}
