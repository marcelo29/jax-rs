package br.com.consultaws.control;

import br.com.consultaws.model.bean.Usuario;
import br.com.consultaws.model.dao.UsuarioDao;

public class UsuarioControl {

	public Usuario selecionarPorLogin(String login) {
		return new UsuarioDao().getInstace().selecionarPorLogin(login);
	}

}