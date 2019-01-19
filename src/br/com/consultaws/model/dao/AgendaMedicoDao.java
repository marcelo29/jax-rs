package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.consultaws.enums.Situacao;
import br.com.consultaws.model.bean.AgendaMedico;
import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.model.conectionfactory.ConectaSql;

public class AgendaMedicoDao {

	// Criação da tabela Agenda Médica
	public static final String TB_AGENDA_MEDICO = "tb_agenda_medico";
	public static final String ID_AGENDA_MEDICO = "id";
	public static final String MEDICO_AGENDA_MEDICO = "medico";
	public static final String DATA_AGENDA_MEDICO = "data";
	public static final String HORA_AGENDA_MEDICO = "hora";
	public static final String LOCAL_ATENDIMENTO_AGENDA_MEDICO = "local_atendimento";
	public static final String SITUACAO_AGENDA_MEDICO = "situacao";

	private MedicoDao mMedicoDAO;
	private LocalAtendimentoDao mLocalAtendimentoDAO;
	private AgendaMedicoDao instance;

	public AgendaMedicoDao getInstance() {
		if (instance == null) {
			instance = new AgendaMedicoDao();
		}
		return instance;
	}

	public AgendaMedicoDao() {
		mMedicoDAO = new MedicoDao();
		mLocalAtendimentoDAO = new LocalAtendimentoDao();
	}

	/**
	 *
	 * @param id
	 * @return AgendaMedico
	 *
	 *         Método que seleciona uma AgendaMedico, através de um Id passado
	 *         por parâmetro
	 */
	public AgendaMedico selecionarPorId(int id) {
		AgendaMedico agendaMedico = null;
		String sql = "select * from " + TB_AGENDA_MEDICO + " where " + ID_AGENDA_MEDICO + "=" + id;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next()) {
				agendaMedico = getAgendaMedico(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendaMedico;
	}

	/**
	 *
	 * @param situacao
	 * @return List<AgendaMedico>
	 *
	 *         Método que retorna uma lista de AgendaMedico, através do valor da
	 *         Situação, passado por parâmetro
	 */
	public List<AgendaMedico> listarPorSituacao(String situacao) {
		List<AgendaMedico> agendasMedico = new ArrayList<>();
		String sql = "select * from " + TB_AGENDA_MEDICO + " where " + SITUACAO_AGENDA_MEDICO + " = '" + situacao + "'";

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				agendasMedico.add(getAgendaMedico(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendasMedico;
	}

	/**
	 *
	 * @param idAgendaMedico
	 * @param situacao
	 * @return boolean
	 *
	 *         Método que altera o valor da Situação, da Agenda Medico,
	 *         especificados por parâmetro e retorna True caso seja bem-sucedida
	 *         e False caso não seja.
	 */
	public boolean alterar(ConsultaMarcada consulta) {
		String sql = "update " + TB_AGENDA_MEDICO + " set " + SITUACAO_AGENDA_MEDICO + " = '" + consulta.getSituacao()
				+ "' where " + ID_AGENDA_MEDICO + " = " + consulta.getIdAgendaMedico();

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ppStm.executeUpdate();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 *
	 * @param localAtendimento
	 * @return List<AgendaMedico>
	 *
	 *         Método que retorna uma lista de AgendaMedico, de um determiando
	 *         Local de Atendimento
	 */
	public List<AgendaMedico> listarPorLocalAtendimento(int localAtendimento) {
		List<AgendaMedico> agendasMedico = new ArrayList<>();
		String sql = "select * from " + TB_AGENDA_MEDICO + " where " + SITUACAO_AGENDA_MEDICO + " = '"
				+ Situacao.DISPONIVEL.getNome() + "' and " + LOCAL_ATENDIMENTO_AGENDA_MEDICO + " = " + localAtendimento;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				agendasMedico.add(getAgendaMedico(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return agendasMedico;
	}

	/**
	 *
	 * @param medico
	 * @return List<AgendaMedico>
	 *
	 *         Método que retorna uma lista de AgendaMedico, de um determinado
	 *         medico
	 */
	public List<AgendaMedico> listarPorMedico(int medico) {
		List<AgendaMedico> agendasMedico = new ArrayList<>();
		String sql = "select * from " + TB_AGENDA_MEDICO + " where " + SITUACAO_AGENDA_MEDICO + " = '"
				+ Situacao.DISPONIVEL.getNome() + "' and " + MEDICO_AGENDA_MEDICO + " = " + medico;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				agendasMedico.add(getAgendaMedico(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return agendasMedico;
	}

	/**
	 *
	 * @param data
	 * @return List<AgendaMedico>
	 *
	 *         Método que retorna uma lista de AgendaMedico, através de uma data
	 *         passada por parâmetro
	 */
	public List<AgendaMedico> listarPorData(Date data) {
		List<AgendaMedico> agendasMedico = new ArrayList<>();
		String sql = "select * from " + TB_AGENDA_MEDICO + " where " + SITUACAO_AGENDA_MEDICO + " = '"
				+ Situacao.DISPONIVEL.getNome() + "' and " + DATA_AGENDA_MEDICO + " = '" + data.toString() + "'";

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				agendasMedico.add(getAgendaMedico(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendasMedico;
	}

	/**
	 *
	 * @param rs
	 * @return AgendaMedico
	 *
	 *         Método que recebe por parâmetro um cursor, na linha onde o
	 *         ponteiro está posicionado, e retorna um Bean de AgendaMedico.
	 *         Utilizado em todos os métodos de seleção e listagem desta classe
	 */
	private AgendaMedico getAgendaMedico(ResultSet rs) {
		AgendaMedico agendaMedico = new AgendaMedico();

		try {
			agendaMedico.setId(rs.getInt(ID_AGENDA_MEDICO));
			agendaMedico.setMedico(mMedicoDAO.selecionarPorId(rs.getInt(MEDICO_AGENDA_MEDICO)));
			agendaMedico.setData(rs.getDate(DATA_AGENDA_MEDICO));
			agendaMedico.setHora(rs.getString(HORA_AGENDA_MEDICO));
			agendaMedico.setLocalAtendimento(
					mLocalAtendimentoDAO.selecionarPorId(rs.getInt(LOCAL_ATENDIMENTO_AGENDA_MEDICO)));

			switch (rs.getString(SITUACAO_AGENDA_MEDICO)) {
			case "D":
				agendaMedico.setSituacao(Situacao.DISPONIVEL);
				break;
			case "M":
				agendaMedico.setSituacao(Situacao.MARCADA);
				break;
			case "C":
				agendaMedico.setSituacao(Situacao.CANCELADA);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendaMedico;
	}
}