package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.consultaws.enums.Situacao;
import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.model.conectionfactory.ConectaSql;
import br.com.consultaws.util.Datas;

public class ConsultaMarcadaDao {

	// Criação da tabela Consulta Marcada
	public static final String TB_CONSULTA_MARCADA = "tb_consulta_marcada";
	public static final String ID_CONSULTA_MARCADA = "id";
	public static final String ID_AGENDA_MEDICO_CONSULTA_MARCADA = "id_agenda_medico";
	public static final String USUARIO_CONSULTA_MARCADA = "usuario";
	public static final String DATA_MARCACAO_CONSULTA_MARCADA = "data_marcacao_consulta";
	public static final String SITUACAO_CONSULTA_MARCADA = "situacao";
	public static final String DATA_CANCELAMENTO_CONSULTA_MARCADA = "data_cancelamento";

	private UsuarioDao mUsuarioDAO;
	private AgendaMedicoDao mAgendaMedicoDAO;

	private ConsultaMarcadaDao instance;

	public ConsultaMarcadaDao getInstace() {
		if (instance == null) {
			instance = new ConsultaMarcadaDao();
			return instance;
		}
		return instance;
	}

	public ConsultaMarcadaDao() {
		mUsuarioDAO = new UsuarioDao();
		mAgendaMedicoDAO = new AgendaMedicoDao();
	}

	public List<ConsultaMarcada> listarMarcadas() {
		List<ConsultaMarcada> consultasMarcadas = new ArrayList<ConsultaMarcada>();

		String sql = "select * from " + TB_CONSULTA_MARCADA + " where " + SITUACAO_CONSULTA_MARCADA + " = '"
				+ Situacao.MARCADA.getNome() + "'";

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				consultasMarcadas.add(getConsultaMarcada(rs));
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return consultasMarcadas;
	}

	public List<ConsultaMarcada> listarMarcadasPorUsuario(int usuario) {
		List<ConsultaMarcada> consultasMarcadas = new ArrayList<>();
		String sql = "select * from " + TB_CONSULTA_MARCADA + " where " + USUARIO_CONSULTA_MARCADA + "=" + usuario
				+ " and " + SITUACAO_CONSULTA_MARCADA + " = '" + Situacao.MARCADA.getNome() + "'";

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				consultasMarcadas.add(getConsultaMarcada(rs));
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return consultasMarcadas;
	}

	/**
	 *
	 * @param consultaMarcada
	 * @param dataCancelamento
	 * @return boolean
	 *
	 *         Método que preenche a data do cancelamento e altera o valor da
	 *         Situação, da Consulta e da Agenda do Médico, para Cancelada, de
	 *         acordo com os dados passados por parâmetro e retorna True caso a
	 *         alteração seja bem-sucedida e False caso não seja.
	 */
	public boolean cancelar(ConsultaMarcada consultaMarcada) {
		String sql = "update " + TB_CONSULTA_MARCADA + " set " + SITUACAO_CONSULTA_MARCADA + " = '"
				+ Situacao.CANCELADA.getNome() + "', " + DATA_CANCELAMENTO_CONSULTA_MARCADA + " = '"
				+ consultaMarcada.getDataCancelamento() + "' where " + ID_CONSULTA_MARCADA + " = "
				+ consultaMarcada.getId();

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ppStm.executeUpdate();

			mAgendaMedicoDAO.alterar(consultaMarcada);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 *
	 * @param consultaMarcada
	 * @return boolean
	 *
	 *         Método responsável por inserir na tabela de Consulta Marcada, um
	 *         BEAN passado por parâmetro e retorna True caso a inserção seja
	 *         bem sucedida, e False caso não seja.
	 */
	public boolean inserir(ConsultaMarcada consultaMarcada) {
		String queryInserir = "insert into " + TB_CONSULTA_MARCADA + " values (null, ?, ?, ?, ?, ?)";

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryInserir);

			ppStm.setInt(1, consultaMarcada.getIdAgendaMedico());
			ppStm.setInt(2, consultaMarcada.getUsuario().getId());
			ppStm.setString(3, Datas.convertDataEmString(consultaMarcada.getDataMarcacaoConsulta()));
			ppStm.setString(4, consultaMarcada.getSituacao().getNome());
			ppStm.setString(5, "");

			ppStm.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param rs
	 * @return ConsultaMarcada
	 *
	 *         Método que recebe por parâmetro um cursor, na linha onde o
	 *         ponteiro está posicionado, e retorna um Bean de ConsultaMarcada.
	 *         Utilizado em todos os métodos de seleção e listagem desta classe.
	 */
	private ConsultaMarcada getConsultaMarcada(ResultSet rs) {
		ConsultaMarcada consultaMarcada = new ConsultaMarcada();

		try {
			consultaMarcada.setId(rs.getInt(ID_CONSULTA_MARCADA));
			consultaMarcada.setUsuario(mUsuarioDAO.selecionarPorId(rs.getInt(USUARIO_CONSULTA_MARCADA)));
			consultaMarcada.setIdAgendaMedico(rs.getInt(ID_AGENDA_MEDICO_CONSULTA_MARCADA));
			Date data = rs.getDate(DATA_MARCACAO_CONSULTA_MARCADA);
			consultaMarcada.setDataMarcacaoConsulta(data);

			switch (rs.getString(SITUACAO_CONSULTA_MARCADA)) {
			case "D":
				consultaMarcada.setSituacao(Situacao.DISPONIVEL);
				break;
			case "M":
				consultaMarcada.setSituacao(Situacao.MARCADA);
				break;
			case "C":
				consultaMarcada.setSituacao(Situacao.CANCELADA);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consultaMarcada;
	}
}