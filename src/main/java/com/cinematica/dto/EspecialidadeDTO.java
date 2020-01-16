package com.cinematica.dto;

/**
 * EspecialidadeDTO
 */
public class EspecialidadeDTO {

    private Integer id;
    private String descricao;
    private String ativo;

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtivo() {
        return this.ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}