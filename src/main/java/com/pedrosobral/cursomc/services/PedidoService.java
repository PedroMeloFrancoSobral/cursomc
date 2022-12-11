package com.pedrosobral.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrosobral.cursomc.domain.Pedido;
import com.pedrosobral.cursomc.repositories.PedidoRepository;
import com.pedrosobral.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(int id) {
		Optional<Pedido> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID: " + id +
				", Tipo: "+ Pedido.class.getName()));
	}
}
