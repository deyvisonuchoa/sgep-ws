package br.com.sgep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sgep.model.Funcionario;
import br.com.sgep.model.dto.VerificadorLoginDTO;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Query(value = "Select count(*) as count FROM FUNCIONARIO WHERE email = :email and senha = :senha group by id", nativeQuery = true )
	Long autenticaLogin(@Param("email") String email, @Param("senha") String senha);
	
	@Query(value = "Select * FROM FUNCIONARIO WHERE email = :email and senha = :senha", nativeQuery = true )
	Funcionario recuperaFuncionarioPorLogin(@Param("email") String email, @Param("senha") String senha);

}