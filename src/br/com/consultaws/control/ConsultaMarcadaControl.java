package br.com.consultaws.control;

import java.util.List;

import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.model.dao.ConsultaMarcadaDao;

public class ConsultaMarcadaControl {

	public List<ConsultaMarcada> listarMarcadas() {
		return new ConsultaMarcadaDao().getInstace().listarMarcadas();
	}

	public List<ConsultaMarcada> listarMarcadasPorUsuario(int usuario) {
		return new ConsultaMarcadaDao().getInstace().listarMarcadasPorUsuario(usuario);
	}

	public boolean cancelar(ConsultaMarcada consultaMarcada) {
		return new ConsultaMarcadaDao().getInstace().cancelar(consultaMarcada);
	}

	public boolean inserir(ConsultaMarcada consultaMarcada) {
		return new ConsultaMarcadaDao().getInstace().inserir(consultaMarcada);
	}
}