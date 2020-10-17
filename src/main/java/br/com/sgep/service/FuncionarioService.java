package br.com.sgep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Funcionario;
import br.com.sgep.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		Funcionario obj = repository.findById(id).get();		
		return obj;
	}

}
