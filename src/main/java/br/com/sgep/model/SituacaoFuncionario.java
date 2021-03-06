package br.com.sgep.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SituacaoFuncionario implements Serializable{
	private static final long serialVersionUID = -5771988520896067366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private Date dataInicioAfastamento;
	
	private Long diasAfastado;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	public SituacaoFuncionario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicioAfastamento() {
		return dataInicioAfastamento;
	}

	public void setDataInicioAfastamento(Date dataInicioAfastamento) {
		this.dataInicioAfastamento = dataInicioAfastamento;
	}

	public Long getDiasAfastado() {
		return diasAfastado;
	}

	public void setDiasAfastado(Long diasAfastado) {
		this.diasAfastado = diasAfastado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SituacaoFuncionario other = (SituacaoFuncionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
