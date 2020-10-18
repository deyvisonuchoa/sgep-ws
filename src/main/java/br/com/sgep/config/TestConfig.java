package br.com.sgep.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sgep.model.Empresa;
import br.com.sgep.model.Funcionario;
import br.com.sgep.model.Setor;
import br.com.sgep.repositories.EmpresaRepository;
import br.com.sgep.repositories.FuncionarioRepository;
import br.com.sgep.repositories.SetorRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Empresa e1 = new Empresa(null, "Desenvolvedores inc", "12124523532");
		Empresa e2 = new Empresa(null, "Proffy", "13254233732");
		
		Setor s1 = new Setor(null, "nome do setor", "centro de custo");
		Setor s2 = new Setor(null, "Ensino", "centro de teste");
		Setor s3 = new Setor(null, "Sofrimento", "LuizClaudio");				
		
		empresaRepository.saveAll(Arrays.asList(e1,e2));
		setorRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		Funcionario f1 = new Funcionario(null, "deyvison", "uchoa", "11668953781", "root", "2019100559", "sexta", 0 , e1, s3);
		Funcionario f2 = new Funcionario(null, "lorena", "ribeiro", "11553944509", "bio123", "2142356", "quarta", 2, e2, s2);
		
		funcionarioRepository.saveAll(Arrays.asList(f1,f2));
		
		LOG.info("TESTE");
		LOG.warn("TESTE");
		LOG.error("TESTE");
		LOG.debug("TESTE");
		
	}

}
