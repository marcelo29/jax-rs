package br.com.consultaws.model.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.consultaws.enums.Situacao;

@XmlRootElement
public class AgendaMedico {

	private int id;
	private Medico medico;
	private Date data;
	private String hora;
	private LocalAtendimento localAtendimento;
	private Situacao situacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}