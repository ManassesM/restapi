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

import com.manadev.domain.Cliente;
import com.manadev.dto.ClienteDTO;
import com.manadev.repositories.ClienteRepository;
import com.manadev.services.exceptions.DataIntegrityException;
import com.manadev.services.exceptions.EmptyResultAcess;
import com.manadev.services.exceptions.ObjectNotFound;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFound("ID não encontrado: " + id + ", Type: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Você não pode remover clientes com entidades relacionadas.");
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultAcess("Cliente não encontrada.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer size, String direction, String orderBy) {
		PageRequest pr = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return repo.findAll(pr);
	}

	// aux

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
