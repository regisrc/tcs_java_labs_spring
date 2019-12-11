package br.com.labssenac.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Professor professor;
	private Materia materia;
	private Sala sala;
	private Date data;
	private Date dataLimite;
	private boolean Ativo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public boolean isAtivo() {
		return Ativo;
	}

	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}

	
	
}
