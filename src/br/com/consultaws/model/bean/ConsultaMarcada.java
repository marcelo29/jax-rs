package br.com.consultaws.model.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.consultaws.enums.Situacao;

@XmlRootElement
public class ConsultaMarcada {

	private int id;
	private int idAgendaMedico;
	private Usuario usuario;
	private Date dataMarcacaoConsulta;
	private Situacao situacao;
	private Date dataCancelamento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAgendaMedico() {
		return idAgendaMedico;
	}

	public void setIdAgendaMedico(int idAgendaMedico) {
		this.idAgendaMedico = idAgendaMedico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataMarcacaoConsulta() {
		return dataMarcacaoConsulta;
	}

	public void setDataMarcacaoConsulta(Date dataMarcacaoConsulta) {
		this.dataMarcacaoConsulta = dataMarcacaoConsulta;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	@Override
	public String toString() {
		return "id: " + id + ", idAgenda: " + idAgendaMedico + ", usuario: " + usuario.getLogin() + ", data: "
				+ dataMarcacaoConsulta + ", situacao: " + situacao.getNome();
	}

}