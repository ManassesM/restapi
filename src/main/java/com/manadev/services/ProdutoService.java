package com.manadev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manadev.domain.Categoria;
import com.manadev.domain.Produto;
import com.manadev.repositories.CategoriaRepository;
import com.manadev.repositories.ProdutoRepository;
import com.manadev.services.exceptions.ObjectNotFound;

@Service
public class ProdutoService {

	@Autowired
	private CategoriaRepository repoCat;

	@Autowired
	private ProdutoRepository repo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFound("ID não encontrado: " + id + ", Type: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer size, String direction,
			String orderBy) {
		PageRequest pr = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repoCat.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pr);
	}

}
