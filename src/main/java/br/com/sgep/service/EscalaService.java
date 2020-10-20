package br.com.sgep.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sgep.model.Escala;
import br.com.sgep.model.Funcionario;
import br.com.sgep.repositories.EscalaRepository;
import br.com.sgep.service.exception.BusinessException;
import br.com.sgep.service.exception.DatabaseException;
import br.com.sgep.service.exception.ObjectNotFoundException;

@Service
public class EscalaService {

	@Autowired
	private EscalaRepository repo;
	
	public List<Escala> findAll(){
		return repo.findAll();
	}
	
	public Escala findById(Long id) {
		Optional<Escala> escala = repo.findById(id);
		return escala.orElseThrow( () -> new ObjectNotFoundException(id)); 
	}
	
	public Escala insert(Escala escala) {
		return repo.save(escala);
	}
	
	public void deleteById(Long id) {
		try {
			repo.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Escala update(Long id, Escala obj) {
		try {
		Escala entity = repo.getOne(id);
		updateData(entity, obj);
		return repo.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	public Escala alocarFuncionario(Long idEscala, Funcionario funcionario) {
		try {
		Escala entity = repo.getOne(idEscala);
		entity.setFuncionario(funcionario);
		return repo.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(idEscala);
		}
	}
	
	public Escala desalocarFuncionario(Long idEscala) {
		Escala escala = findById(idEscala);
		if(escala.getFuncionario() == null) {
			throw new BusinessException("Não existe funcionário cadastrado para a escala escolhida");
		}
		escala.setFuncionario(null);
		//escala.setId(idEscala);
		return repo.save(escala);
	}

	private void updateData(Escala entity, Escala obj) {
		entity.setNomeEscala(obj.getNomeEscala());
		entity.setHoraInicial(obj.getHoraInicial());
		entity.setHoraFinal(obj.getHoraFinal());
		entity.setHoraIntervalo(obj.getHoraIntervalo());
		entity.setLimiteHorasBanco(obj.getLimiteHorasBanco());
		entity.setFuncionario(obj.getFuncionario());
	}
	
}
