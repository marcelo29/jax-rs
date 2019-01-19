package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.consultaws.model.bean.LocalAtendimento;
import br.com.consultaws.model.conectionfactory.ConectaSql;

public class LocalAtendimentoDao {

	// Criação da tabela Localatendimento
	public static final String TB_LOCAL_ATENDIMENTO = "tb_local_atendimento";
	public static final String ID_LOCAL_ATENDIMENTO = "id";
	public static final String NOME_LOCAL_ATENDIMENTO = "nome";
	public static final String ENDERECO_LOCAL_ATENDIMENTO = "endereco";

	private LocalAtendimentoDao instance;

	public LocalAtendimentoDao getInstace() {
		if (instance == null) {
			instance = new LocalAtendimentoDao();
			return instance;
		}
		return instance;
	}

	/**
	 *
	 * @param id
	 * @return LocalAtendimento
	 *
	 *         Método que seleciona um LocalAtendimento, através de um Id
	 *         passado por parâmetro
	 */
	public LocalAtendimento selecionarPorId(int id) {
		LocalAtendimento localAtendimento = null;

		String sql = "select * from " + TB_LOCAL_ATENDIMENTO + " where " + ID_LOCAL_ATENDIMENTO + "=" + id;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next()) {
				localAtendimento = getLocalAtendimento(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return localAtendimento;
	}

	/**
	 *
	 * @return List<LocalAtendimento>
	 *
	 *         Método que retorna a lista de todas os Locais de Atendimento
	 */
	public List<LocalAtendimento> listar() {
		List<LocalAtendimento> locaisAtendimento = new ArrayList<LocalAtendimento>();

		String sql = "select * from " + TB_LOCAL_ATENDIMENTO;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				locaisAtendimento.add(getLocalAtendimento(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return locaisAtendimento;
	}

	/**
	 *
	 * @param cursor
	 * @return LocalAtendimento
	 *
	 *         Método que recebe por parâmetro um cursor, na linha onde o
	 *         ponteiro está posicionado, e retorna um Bean de LocalAtendimento.
	 *         Utilizado em todos os métodos de seleção e listagem desta classe
	 */
	private LocalAtendimento getLocalAtendimento(ResultSet rs) {
		LocalAtendimento localAtendimento = new LocalAtendimento();

		try {
			localAtendimento.setId(rs.getInt(ID_LOCAL_ATENDIMENTO));
			localAtendimento.setNome(rs.getString(NOME_LOCAL_ATENDIMENTO));
			localAtendimento.setEndereco(rs.getString(ENDERECO_LOCAL_ATENDIMENTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localAtendimento;
	}
}