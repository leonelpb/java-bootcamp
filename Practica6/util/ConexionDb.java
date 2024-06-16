package Practica6.Practica6.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {
	private static final String URL = "jdbc:mysql://localhost:3306/Dietetica";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión a la base de datos exitosa.");
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
		return connection;
	}
}
