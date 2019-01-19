package br.com.consultaws.model.conectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaSql {

	//postgresql
	private static final String URL = "jdbc:postgresql://localhost/marcar_consulta";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";

	public static Connection obtemConexao() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}