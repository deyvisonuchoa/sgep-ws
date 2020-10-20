package br.com.sgep.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Funcionario;
import br.com.sgep.repositories.FuncionarioRepository;
import br.com.sgep.service.exception.DatabaseException;
import br.com.sgep.service.exception.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow( ()-> new ObjectNotFoundException (id) );
	}
	
	public Funcionario insert(Funcionario obj) {
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
	
	public Funcionario update(Long id, Funcionario obj) {
		try {
		Funcionario entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Funcionario entity, Funcionario obj) {
		entity.setNome(obj.getNome());	
		entity.setSobrenome(obj.getSobrenome());
		entity.setEmail(obj.getEmail());
		entity.setCpf(obj.getCpf());
		entity.setDiafolga(obj.getDiafolga());
		entity.setMatricula(obj.getMatricula());
		entity.setStatus(obj.getStatus());
		entity.setSetor(obj.getSetor());
		entity.setSituacao(obj.getSituacao());
		entity.setEmpresa(obj.getEmpresa());
	}

}
