package br.com.consultaws.model.conectionfactory;

import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		System.out.println(ConectaSql.obtemConexao().toString());
	}

}