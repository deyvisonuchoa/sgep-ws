package br.com.sgep.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Setor implements Serializable{
	private static final long serialVersionUID = -6854577051231769840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeSetor;
	
	private String centroCusto;
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor")
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Setor() {
	
	}

	public Setor(Long id, String nomeSetor, String centroCusto) {
		super();
		this.id = id;
		this.nomeSetor = nomeSetor;
		this.centroCusto = centroCusto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}	
	
}
