package br.com.consultaws.control;

import java.sql.Date;
import java.util.List;

import br.com.consultaws.model.bean.AgendaMedico;
import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.model.dao.AgendaMedicoDao;

public class AgendaMedicoControl {

	public AgendaMedico selecionarPorId(int id) {
		return new AgendaMedicoDao().getInstance().selecionarPorId(id);
	}

	public List<AgendaMedico> listarPorSituacao(String situacao) {
		return new AgendaMedicoDao().getInstance().listarPorSituacao(situacao);
	}

	public boolean alterar(ConsultaMarcada consulta) {
		return new AgendaMedicoDao().getInstance().alterar(consulta);
	}

	public List<AgendaMedico> listarPorLocalAtendimento(int localAtendimento) {
		return new AgendaMedicoDao().getInstance().listarPorLocalAtendimento(localAtendimento);
	}

	public List<AgendaMedico> listarPorMedico(int medico) {
		return new AgendaMedicoDao().getInstance().listarPorMedico(medico);
	}

	public List<AgendaMedico> listarPorData(Date data) {
		return new AgendaMedicoDao().getInstance().listarPorData(data);
	}

}