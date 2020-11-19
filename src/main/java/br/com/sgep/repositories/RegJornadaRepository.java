package br.com.sgep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sgep.model.RegJornada;

public interface RegJornadaRepository extends JpaRepository<RegJornada, Long>{

	@Query(value = "Select * FROM REG_JORNADA WHERE id_funcionario = :id ORDER BY data DESC LIMIT 1", nativeQuery = true )
	RegJornada ultimoRegistro(@Param("id") Long id);
	
	@Query(value = "Select * FROM REG_JORNADA WHERE id_funcionario = :id ", nativeQuery = true )
	List<RegJornada> findAllByFuncionario(@Param("id") Long idFuncionario);
	
}
