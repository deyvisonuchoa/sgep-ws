package br.com.sgep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Setor;
import br.com.sgep.repositories.SetorRepository;

@Service
public class SetorService {

	@Autowired
	private SetorRepository repository;

	public List<Setor> findAll() {
		return repository.findAll();
	}

	public Setor findById(Long id) {
		Setor obj = repository.findById(id).get();		
		return obj;
	}
	
}
