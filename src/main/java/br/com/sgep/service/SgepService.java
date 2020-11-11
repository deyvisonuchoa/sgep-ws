package br.com.sgep.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sgep.config.Instanciation;
import br.com.sgep.model.Empresa;
import br.com.sgep.model.Escala;
import br.com.sgep.model.Funcionario;
import br.com.sgep.model.RegJornada;
import br.com.sgep.model.Setor;
import br.com.sgep.model.dto.LoginDTO;
import br.com.sgep.repositories.EmpresaRepository;
import br.com.sgep.repositories.EscalaRepository;
import br.com.sgep.repositories.FuncionarioRepository;
import br.com.sgep.repositories.RegJornadaRepository;
import br.com.sgep.repositories.SetorRepository;
import br.com.sgep.service.exception.BusinessException;
import br.com.sgep.service.exception.DatabaseException;
import br.com.sgep.service.exception.ObjectNotFoundException;

@Service
public class SgepService {

	@Autowired
	private FuncionarioRepository funcionarioRepo;

	@Autowired
	private EmpresaRepository empresaRepo;

	@Autowired
	private SetorRepository setorRepo;

	@Autowired
	private EscalaRepository escalaRepo;

	@Autowired
	private RegJornadaRepository registroRepo;
	
	static final Logger LOG = LoggerFactory.getLogger(Instanciation.class);
	
	//LOGIN
	public Funcionario autenticaLogin(LoginDTO login) {		
		Long count = funcionarioRepo.autenticaLogin(login.getEmail(), login.getSenha());	
		
		if(count > 0)
			return funcionarioRepo.recuperaFuncionarioPorLogin(login.getEmail(), login.getSenha());
		
		return null;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	}

	// Funcionário
	public List<Funcionario> recuperaFuncionarios() {
		return funcionarioRepo.findAll();
	}

	public Funcionario recuperaFuncionarioPorId(Long id) {
		Optional<Funcionario> obj = funcionarioRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public Funcionario cadastrarFuncionario(Funcionario obj) {
		return funcionarioRepo.save(obj);
	}

	public void removeFuncionario(Long id) {
		try {
			funcionarioRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Funcionario atualizaFuncionario(Long id, Funcionario obj) {
		try {
			Funcionario entity = funcionarioRepo.getOne(id);
			atualizaDadosFuncionario(entity, obj);
			return funcionarioRepo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void atualizaDadosFuncionario(Funcionario entity, Funcionario obj) {
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

	public RegJornada lancarEntrada(Long id) {
		
		RegJornada ultimoRegistro = registroRepo.ultimoRegistro(id);
		
		if(ultimoRegistro != null)
		if(ultimoRegistro.getHoraSaida() == null)
			throw new BusinessException("O ultimo lancamento não foi fechado ");
		
		RegJornada novoRegistro = new RegJornada(new Date(), LocalTime.now(), recuperaFuncionarioPorId(id));
		
		return registroRepo.save(novoRegistro);
	}

	public RegJornada lancarSaida(Long id) {
		
		RegJornada ultimoRegistro = registroRepo.ultimoRegistro(id);
		
		if((ultimoRegistro == null) || (ultimoRegistro.getHoraSaida() != null)) {
			throw new BusinessException("Não existe lançamento para ser fechado ");
		}
		
		ultimoRegistro.setHoraSaida(LocalTime.now());
		
		return registroRepo.save(ultimoRegistro);
	}

	// Empresa
	public List<Empresa> recuperaEmpresas() {
		return empresaRepo.findAll();
	}

	public Empresa recuperaEmpresaPorId(Long id) {
		Optional<Empresa> obj = empresaRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public Empresa cadastraEmpresa(Empresa obj) {
		return empresaRepo.save(obj);
	}

	public void removeEmpresa(Long id) {
		try {
			empresaRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Empresa atualizaEmpresa(Long id, Empresa obj) {
		try {
			Empresa entity = empresaRepo.getOne(id);
			atualizaDadosEmpresa(entity, obj);
			return empresaRepo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void atualizaDadosEmpresa(Empresa entity, Empresa obj) {
		entity.setRazaoSocial(obj.getRazaoSocial());
		entity.setCnpj(obj.getCnpj());
	}

	// Setor
	public List<Setor> recuperaSetores() {
		return setorRepo.findAll();
	}

	public Setor recuperaSetorPorId(Long id) {
		Setor obj = setorRepo.findById(id).get();
		return obj;
	}

	// Escala
	public List<Escala> recuperarEscalas() {
		return escalaRepo.findAll();
	}

	public Escala recuperarEscalaPorId(Long id) {
		Optional<Escala> escala = escalaRepo.findById(id);
		return escala.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public Escala cadastrarEscala(Escala escala) {
		return escalaRepo.save(escala);
	}

	public void removerEscala(Long id) {
		try {
			escalaRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Escala atualizarEscala(Long id, Escala obj) {
		try {
			Escala entity = escalaRepo.getOne(id);
			atualizarDadosEscala(entity, obj);
			return escalaRepo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void atualizarDadosEscala(Escala entity, Escala obj) {
		entity.setNomeEscala(obj.getNomeEscala());
		entity.setHoraInicial(obj.getHoraInicial());
		entity.setHoraFinal(obj.getHoraFinal());
		entity.setHoraIntervalo(obj.getHoraIntervalo());
		entity.setLimiteHorasBanco(obj.getLimiteHorasBanco());
	}

}
