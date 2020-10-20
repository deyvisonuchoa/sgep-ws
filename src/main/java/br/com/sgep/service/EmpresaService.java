package br.com.sgep.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Empresa;
import br.com.sgep.model.Empresa;
import br.com.sgep.model.Setor;
import br.com.sgep.repositories.EmpresaRepository;
import br.com.sgep.service.exception.DatabaseException;
import br.com.sgep.service.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repository;

	public List<Empresa> findAll() {
		return repository.findAll();
	}

	public Empresa findById(Long id) {
		Optional<Empresa> obj = repository.findById(id);		
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) );
	}
	
	public Empresa insert(Empresa obj) {
		return repository.save(obj);
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Empresa update(Long id, Empresa obj) {
		try {
		Empresa entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Empresa entity, Empresa obj) {
		entity.setRazaoSocial(obj.getRazaoSocial());	
		entity.setCnpj(obj.getCnpj());
	}
}
