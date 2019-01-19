package br.com.consultaws.control;

import java.util.List;

import br.com.consultaws.model.bean.Especialidade;
import br.com.consultaws.model.dao.EspecialidadeDao;

public class EspecialidadeControl {

	public Especialidade selecionarPorId(int id) {
		return new EspecialidadeDao().getInstace().selecionarPorId(id);
	}

	public List<Especialidade> listar() {
		return new EspecialidadeDao().getInstace().listar();
	}

}