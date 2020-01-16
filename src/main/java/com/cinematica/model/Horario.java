package com.cinematica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/*private Empresa empresa;*/	
	private String horarioInicio;
	private String horarioFim;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="horario_fim")
	public String getHorarioFim() {
		return horarioFim;
	}
	
	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}
	
	@Column(name="horario_inicio")
	public String getHorarioInicio() {
		return horarioInicio;
	}
	
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
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
		if (!(obj instanceof Horario))
			return false;
		Horario other = (Horario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
