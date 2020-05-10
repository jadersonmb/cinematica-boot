package com.cinematica.enums;

public enum TipoLancamento {

	Receita("Receita"), 
	Despesa("Despesa");

	private String value;

	private TipoLancamento(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
