package br.com.consultaws.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.consultaws.model.bean.Medico;
import br.com.consultaws.model.conectionfactory.ConectaSql;

public class MedicoDao {

	// Criação da tabela Médico
	public static final String TB_MEDICO = "TB_MEDICO";
	public static final String ID_MEDICO = "id";
	public static final String NOME_MEDICO = "nome";
	public static final String CRM_MEDICO = "crm";
	public static final String ESPECIALIDADE_MEDICO = "especialidade";

	private EspecialidadeDao mEspecialidadeDAO;
	private MedicoDao instance;

	public MedicoDao getInstace() {
		if (instance == null) {
			instance = new MedicoDao();
			return instance;
		}
		return instance;
	}

	public MedicoDao() {
		mEspecialidadeDAO = new EspecialidadeDao();
	}

	// Método que seleciona um Medico, através de um Id passado por parâmetro
	public Medico selecionarPorId(int id) {
		Medico medico = null;

		String condicao = ID_MEDICO + "=" + id;
		String queryPesquisa = "select * from " + TB_MEDICO + " where " + condicao;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryPesquisa);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next()) {
				medico = getMedico(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medico;
	}

	// Método que retorna a lista de todos os Médicos
	public List<Medico> listar() {
		List<Medico> medicos = new ArrayList<Medico>();

		String select = "select * from " + TB_MEDICO;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(select);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next())
				medicos.add(getMedico(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicos;
	}

	// Método que retorna a lista dos Medicos que possuem determinada
	// especialidade informada no parâmetro
	public List<Medico> listarPorEspecialidade(int especialidade) {
		List<Medico> medicos = new ArrayList<Medico>();
		String sql = "select * from " + TB_MEDICO + " where " + ESPECIALIDADE_MEDICO + " = " + especialidade;

		try {
			Connection con = ConectaSql.obtemConexao();

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(sql);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				medicos.add(getMedico(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicos;
	}

	private Medico getMedico(ResultSet rs) {
		Medico medico = new Medico();

		try {
			medico.setId(rs.getInt(ID_MEDICO));
			medico.setNome(rs.getString(NOME_MEDICO));
			medico.setCrm(rs.getInt(CRM_MEDICO));
			medico.setEspecialidade(mEspecialidadeDAO.selecionarPorId(rs.getInt(ESPECIALIDADE_MEDICO)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medico;
	}
}