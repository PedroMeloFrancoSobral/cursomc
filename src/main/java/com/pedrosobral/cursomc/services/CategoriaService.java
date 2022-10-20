package com.pedrosobral.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrosobral.cursomc.domain.Categoria;
import com.pedrosobral.cursomc.repositories.CategoriaRepository;
import com.pedrosobral.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(int id) {
		Optional<Categoria> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID: " + id +
				", Tipo: "+ Categoria.class.getName()));
	}
}
