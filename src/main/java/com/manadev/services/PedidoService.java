package com.manadev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manadev.domain.Pedido;
import com.manadev.repositories.PedidoRepository;
import com.manadev.services.exceptions.ObjectNotFound;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFound("ID não encontrado: " + id + ", Type: " + Pedido.class.getName()));
	}
}
