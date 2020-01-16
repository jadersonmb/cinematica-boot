package com.cinematica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "horario_disponivel")
public class HorarioDisponivel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private DataFalta dataFalta;
	private Horario horario;

	public HorarioDisponivel() {}
	
	public HorarioDisponivel(Horario horario, DataFalta dataFalta) {
		this.horario = horario;
		this.dataFalta = dataFalta;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "horario_id", nullable = false)
	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	

	@ManyToOne
	@JoinColumn(name = "data_falta_id", nullable = false)
	public DataFalta getDataFalta() {
		return dataFalta;
	}

	public void setDataFalta(DataFalta dataFalta) {
		this.dataFalta = dataFalta;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFalta == null) ? 0 : dataFalta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HorarioDisponivel))
			return false;
		HorarioDisponivel other = (HorarioDisponivel) obj;
		if (dataFalta == null) {
			if (other.dataFalta != null)
				return false;
		} else if (!dataFalta.equals(other.dataFalta))
			return false;
		return true;
	}

}
