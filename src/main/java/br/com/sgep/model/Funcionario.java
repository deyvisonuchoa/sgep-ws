package br.com.sgep.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sgep.entities.enums.Privilegio;

@Entity
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String sobrenome;

	private String cpf;

	private String email;

	private String senha;

	private String matricula;

	private String diafolga;

	private Integer privilegio;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_gestor")
	private Funcionario gestor;

	// Associaçoes
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "id_Setor")
	private Setor setor;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario")
    private SituacaoFuncionario situacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_escala")
	private Escala escala ;
	
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<RegJornada> jornadas = new ArrayList<>();

	public Funcionario() {

	}	

	public Funcionario(Long id, String nome, String sobrenome, String cpf, String senha, String matricula,
			String diafolga, Integer privilegio, Empresa empresa, Setor setor, String email, String status, Escala escala) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.diafolga = diafolga;
		this.privilegio = privilegio;
		this.empresa = empresa;
		this.setor = setor;
		this.email = email;
		this.status = status;
		this.escala = escala;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDiafolga() {
		return diafolga;
	}

	public void setDiafolga(String diafolga) {
		this.diafolga = diafolga;
	}

	public Privilegio getPrivilegio() {
		return Privilegio.valueOf(privilegio);
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio.getCodigo();
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Setor getSetor() {
		return setor;
	}

	public SituacaoFuncionario getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoFuncionario situacao) {
		this.situacao = situacao;
	}

	public List<RegJornada> getJornadas() {
		return jornadas;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Escala getEscala() {
		return escala;
	}

	public void setEscala(Escala escala) {
		this.escala = escala;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
