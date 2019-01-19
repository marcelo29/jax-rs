package br.com.consultaws.control;

import java.util.List;

import br.com.consultaws.model.bean.Medico;
import br.com.consultaws.model.dao.MedicoDao;

public class MedicoControl {

	public Medico selecionarPorId(int id) {
		return new MedicoDao().getInstace().selecionarPorId(id);
	}

	public List<Medico> listar() {
		return new MedicoDao().getInstace().listar();
	}

	public List<Medico> listarPorEspecialidade(int especialidade) {
		return new MedicoDao().getInstace().listarPorEspecialidade(especialidade);
	}

}