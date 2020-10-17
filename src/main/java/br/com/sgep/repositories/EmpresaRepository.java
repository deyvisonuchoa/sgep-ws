package br.com.sgep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sgep.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
