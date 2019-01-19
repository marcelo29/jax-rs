package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import br.com.consultaws.model.bean.Usuario;
import br.com.consultaws.model.conectionfactory.ConectaSql;

// indica o path para acessar a classe no consumidor
public class UsuarioDao {

	// Criação da tabela Usuario
	public static final String TB_USUARIO = "tb_usuario";
	public static final String ID_USUARIO = "id";
	public static final String LOGIN_USUARIO = "login";
	public static final String SENHA_USUARIO = "senha";
	public static final String PERFIL_USUARIO = "perfil";
	public static final String EMAIL_USUARIO = "email";

	private UsuarioDao instance = null;

	// garanti uma instancia do objeto
	public UsuarioDao getInstace() {
		if (instance == null) {
			instance = new UsuarioDao();
			// return instance;
		}
		return instance;
	}

	// usado metodo get do protocolo http para a requisicao
	// selecion usuario pelo login
	public Usuario selecionarPorLogin(String login) {
		Usuario user = null;

		try {
			Connection con = ConectaSql.obtemConexao();

			String queryPesquisa = "select * from " + TB_USUARIO + " where " + LOGIN_USUARIO + "='" + login + "'";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryPesquisa);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next())
				user = getUsuario(rs);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// seleciona usuario pelo id
	public Usuario selecionarPorId(int id) {
		Usuario user = null;

		try {
			Connection con = ConectaSql.obtemConexao();

			String condicao = ID_USUARIO + "=" + id + "";
			String queryPesquisa = "select * from " + TB_USUARIO + " where " + condicao;

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryPesquisa);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next())
				user = getUsuario(rs);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// pega usuario por meio do result set
	private Usuario getUsuario(ResultSet rs) {
		Usuario user = new Usuario();

		try {
			user.setId(rs.getInt(ID_USUARIO));
			user.setLogin(rs.getString(LOGIN_USUARIO));
			user.setSenha(rs.getString(SENHA_USUARIO));
			user.setPerfil(rs.getString(PERFIL_USUARIO));
			user.setEmail(rs.getString(EMAIL_USUARIO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}