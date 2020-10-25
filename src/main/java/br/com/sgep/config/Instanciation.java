package br.com.sgep.config;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sgep.model.Empresa;
import br.com.sgep.model.Escala;
import br.com.sgep.model.Funcionario;
import br.com.sgep.model.RegJornada;
import br.com.sgep.model.Setor;
import br.com.sgep.repositories.EmpresaRepository;
import br.com.sgep.repositories.EscalaRepository;
import br.com.sgep.repositories.FuncionarioRepository;
import br.com.sgep.repositories.RegJornadaRepository;
import br.com.sgep.repositories.SetorRepository;

@Configuration
@Profile("dev")
public class Instanciation implements CommandLineRunner{
	
	static final Logger LOG = LoggerFactory.getLogger(Instanciation.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EscalaRepository escalaRepository;
	
	@Autowired
	private RegJornadaRepository registroRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Empresa empresa1 = new Empresa(null, "Desenvolvedores inc", "12124523532");
		Empresa empresa2 = new Empresa(null, "Proffy", "13254233732");
		
		Setor s1 = new Setor(null, "nome do setor", "centro de custo");
		Setor s2 = new Setor(null, "Ensino", "centro de teste");
		Setor s3 = new Setor(null, "Sofrimento", "LuizClaudio");		

		Escala escala1 = new Escala(null, "TX2EHY", LocalTime.parse("09:34:40"), LocalTime.parse("12:34:40"), LocalTime.parse("16:34:40"), 40);
		Escala escala2 = new Escala(null, "TX2YBJ", LocalTime.parse("09:34:40"), LocalTime.parse("15:12:44"), LocalTime.parse("19:30:56"), 40);
		Escala escala3 = new Escala(null, "TX45GB", LocalTime.parse("21:54:33"), LocalTime.parse("21:54:33"), LocalTime.parse("19:30:56"), 40);		
		
		empresaRepository.saveAll(Arrays.asList(empresa1,empresa2));
		setorRepository.saveAll(Arrays.asList(s1,s2,s3));				
		escalaRepository.saveAll(Arrays.asList(escala1,escala2,escala3));
		
 		Funcionario f1 = new Funcionario(null, "deyvison", "uchoa", "11668953781", "root", "2019100559", "sexta", 0 , empresa1, s3, "deyvisonuchoa@Hotmail.com", "Ativo", escala1);
		Funcionario f2 = new Funcionario(null, "lorena", "ribeiro", "11553944509", "bio123", "2142356", "quarta", 2, empresa2, s2, "lorerj11@gmail.com", "ativo",escala1);
		Funcionario f3 = new Funcionario(null, "carlos", "fernando", "11587646579", "teste1", "423w6756v", "terca", 2, empresa2, s1, "carlossilva@ig.com.br", "inativo", escala3);
		
		funcionarioRepository.saveAll(Arrays.asList(f1,f2,f3));		
		
		RegJornada registro1 = new RegJornada(null, new Date(), LocalTime.parse("09:34:40"), LocalTime.parse("16:34:40"), null, null, null, "observacao", f1, null);
		
		LOG.info("TESTE info");
		LOG.warn("TESTE warn");
		LOG.error("TESTE erro");
		LOG.debug("TESTE debug");
		
		
	}

}
