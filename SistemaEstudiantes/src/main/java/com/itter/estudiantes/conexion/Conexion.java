package com.itter.estudiantes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Connection getConnection() {
		 Connection conexion = null;
		 String baseDeDatos = "estudiantes_db";
		 var url = "jdbc:mysql://localhost:3306/" + baseDeDatos;
		 var usuario = "root";
		 var password = "root";
		 //Cargamos la clase del driver mysql en memmoria
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conexion = DriverManager.getConnection(url, usuario, password);
		 } catch (ClassNotFoundException | SQLException e) {
			 System.out.println("Ocurrió un error: " +e.getMessage());
		 }
		 
		 
		 return conexion;
	}
	
	public static void main(String[] args) {
		var conexion = Conexion.getConnection();
		if(conexion != null) 
			System.out.println("Conexion exitosa: " +conexion);
		else 
			System.out.println("Error al conectarse");
	}

}
