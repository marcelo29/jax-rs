package br.com.consultaws.enums;

public enum Situacao {
	DISPONIVEL("D"), MARCADA("M"), CANCELADA("C");

	private final String nome;

	Situacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
