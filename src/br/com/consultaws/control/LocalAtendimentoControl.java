package br.com.consultaws.control;

import java.util.List;

import br.com.consultaws.model.bean.LocalAtendimento;
import br.com.consultaws.model.dao.LocalAtendimentoDao;

public class LocalAtendimentoControl {

	public LocalAtendimento selecionarPorId(int id) {
		return new LocalAtendimentoDao().getInstace().selecionarPorId(id);
	}

	public List<LocalAtendimento> listar() {
		return new LocalAtendimentoDao().getInstace().listar();
	}

}