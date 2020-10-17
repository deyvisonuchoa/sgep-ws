package br.com.sgep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Empresa;
import br.com.sgep.model.Setor;
import br.com.sgep.repositories.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repository;

	public List<Empresa> findAll() {
		return repository.findAll();
	}

	public Empresa findById(Long id) {
		Empresa obj = repository.findById(id).get();		
		return obj;
	}
}
