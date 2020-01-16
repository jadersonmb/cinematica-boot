package com.cinematica.dto;

/**
 * HorarioDTO
 */
public class HorarioDTO {

    private Integer id;
    private String horarioInicio;
    private String horarioFim;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorarioInicio() {
        return this.horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return this.horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

}