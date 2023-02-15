package org.avismart.utils.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private static Connection cnx = null;
public static Connection getConnection() {
	try {
		if (cnx == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/avismart?serverTimezone=America/Bogota", "root", "");
		}
		return cnx;
	} catch (Exception e) {
		throw new RuntimeException("Error al crear la conexión", e);
	}

	
}
}