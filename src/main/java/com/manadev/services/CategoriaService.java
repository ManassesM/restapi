package com.manadev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manadev.domain.Categoria;
import com.manadev.dto.CategoriaDTO;
import com.manadev.repositories.CategoriaRepository;
import com.manadev.services.exceptions.DataIntegrityException;
import com.manadev.services.exceptions.EmptyResultAcess;
import com.manadev.services.exceptions.ObjectNotFound;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFound("ID não encontrado: " + id + ", Type: " + Categoria.class.getName()));
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Você não pode remover categorias que têm produtos.");
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultAcess("Categoria não encontrada.");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer size, String direction, String orderBy) {
		PageRequest pr = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return repo.findAll(pr);
	}

	// aux

	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
