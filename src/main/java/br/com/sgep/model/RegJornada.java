package br.com.sgep.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REG_JORNADA")
public class RegJornada implements Serializable{
	private static final long serialVersionUID = -7740398808666140333L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date data;
	
	private LocalTime horaEntrada;
	
	private LocalTime horaSaida;
	
	private LocalTime horaExtraNormal;
	
	private LocalTime horaExtraFeriado;
	
	private LocalTime horaBanco;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	@OneToOne(mappedBy = "registro", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private AprvHrExtra aprovacao;
	
	public RegJornada() {
		
	}
	
	

	public RegJornada(Date data, LocalTime horaEntrada, Funcionario funcionario) {
		super();
		this.data = data;
		this.horaEntrada = horaEntrada;
		this.funcionario = funcionario;
	}



	public RegJornada(Long id, Date data, LocalTime horaEntrada, LocalTime horaSaida, LocalTime horaExtraNormal,
			LocalTime horaExtraFeriado, LocalTime horaBanco, String observacao, Funcionario funcionario,
			AprvHrExtra aprovacao) {
		super();
		this.id = id;
		this.data = data;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.horaExtraNormal = horaExtraNormal;
		this.horaExtraFeriado = horaExtraFeriado;
		this.horaBanco = horaBanco;
		this.observacao = observacao;
		this.funcionario = funcionario;
		this.aprovacao = aprovacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public LocalTime getHoraExtraNormal() {
		return horaExtraNormal;
	}

	public void setHoraExtraNormal(LocalTime horaExtraNormal) {
		this.horaExtraNormal = horaExtraNormal;
	}

	public LocalTime getHoraExtraFeriado() {
		return horaExtraFeriado;
	}

	public void setHoraExtraFeriado(LocalTime horaExtraFeriado) {
		this.horaExtraFeriado = horaExtraFeriado;
	}

	public LocalTime getHoraBanco() {
		return horaBanco;
	}

	public void setHoraBanco(LocalTime horaBanco) {
		this.horaBanco = horaBanco;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public AprvHrExtra getAprovacao() {
		return aprovacao;
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
		RegJornada other = (RegJornada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
