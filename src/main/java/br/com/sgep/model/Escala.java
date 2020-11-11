package br.com.sgep.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Escala implements Serializable{
	private static final long serialVersionUID = -6854577051231769840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeEscala;

	private LocalTime horaInicial;

	private LocalTime horaFinal;

	private LocalTime horaIntervalo;
	
	private int limiteHorasBanco;
	
	@OneToMany(mappedBy = "escala")
	private List<Funcionario> funcionarios = new ArrayList<>();

	public Escala() {
	
	}

	public Escala(Long id, String nomeEscala, LocalTime horaInicial, LocalTime horaFinal, LocalTime horaIntervalo,
			int limiteHorasBanco) {
		super();
		this.id = id;
		this.nomeEscala = nomeEscala;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.horaIntervalo = horaIntervalo;
		this.limiteHorasBanco = limiteHorasBanco;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEscala() {
		return nomeEscala;
	}

	public void setNomeEscala(String nomeEscala) {
		this.nomeEscala = nomeEscala;
	}

	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public LocalTime getHoraIntervalo() {
		return horaIntervalo;
	}

	public void setHoraIntervalo(LocalTime horaIntervalo) {
		this.horaIntervalo = horaIntervalo;
	}

	public int getLimiteHorasBanco() {
		return limiteHorasBanco;
	}

	public void setLimiteHorasBanco(int limiteHorasBanco) {
		this.limiteHorasBanco = limiteHorasBanco;
	}	

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
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
		Escala other = (Escala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
