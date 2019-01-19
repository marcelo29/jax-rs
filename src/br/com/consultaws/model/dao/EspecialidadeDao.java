package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import com.mysql.jdbc.PreparedStatement;

import br.com.consultaws.model.bean.Especialidade;
import br.com.consultaws.model.conectionfactory.ConectaSql;

public class EspecialidadeDao {

	// Criação da tabela Especialidades
	public static final String TB_ESPECIALIDADE = "tb_especialidade";
	public static final String ID_ESPECIALIDADE = "id";
	public static final String NOME_ESPECIALIDADE = "nome";

	private EspecialidadeDao instance = null;

	public EspecialidadeDao getInstace() {
		if (instance == null) {
			instance = new EspecialidadeDao();
			return instance;
		}
		return instance;
	}

	// seleciona especialidade pelo id
	public Especialidade selecionarPorId(@PathParam("id") int id) {
		Especialidade especialidade = null;

		String sql = "select * from " + TB_ESPECIALIDADE + " where " + ID_ESPECIALIDADE + "=" + id;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next()) {
				especialidade = getEspecialidade(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return especialidade;
	}

	// Método que retorna a lista de todas as Especialidades
	public List<Especialidade> listar() {
		List<Especialidade> especialidades = new ArrayList<Especialidade>();

		try {
			Connection con = ConectaSql.obtemConexao();

			String qry = "select * from " + TB_ESPECIALIDADE;

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(qry);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				especialidades.add(getEspecialidade(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return especialidades;
	}

	/**
	 *
	 * @param cursor
	 * @return Especialidade
	 *
	 *         Método que recebe por parâmetro um cursor, na linha onde o
	 *         ponteiro está posicionado, e retorna um Bean de Especialidade. A
	 *         principal utilidade do método é o reuso do mesmo, em todos os
	 *         métodos de seleção e listagem desta classe
	 */
	private Especialidade getEspecialidade(ResultSet rs) {
		Especialidade especialidade = new Especialidade();

		try {
			especialidade.setId(rs.getInt(ID_ESPECIALIDADE));
			especialidade.setNome(rs.getString(NOME_ESPECIALIDADE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return especialidade;
	}

}