package com.pedrosobral.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrosobral.cursomc.domain.Cliente;
import com.pedrosobral.cursomc.repositories.ClienteRepository;
import com.pedrosobral.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(int id) {
		Optional<Cliente> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID: " + id +
				", Tipo: "+ Cliente.class.getName()));
	}
}