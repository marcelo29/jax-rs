package br.com.consultaws.enums;

public enum Perfil {
	USER("U"), ADM("A");

	private final String nome;

	Perfil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
