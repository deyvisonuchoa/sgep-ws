package br.com.sgep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sgep.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
