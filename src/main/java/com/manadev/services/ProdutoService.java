package com.manadev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manadev.domain.Produto;
import com.manadev.repositories.ProdutoRepository;
import com.manadev.services.exceptions.ObjectNotFound;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFound("ID não encontrado: " + id + ", Type: " + Produto.class.getName()));
	}
}
