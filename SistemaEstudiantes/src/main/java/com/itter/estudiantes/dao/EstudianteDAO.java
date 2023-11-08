package com.itter.estudiantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.itter.estudiantes.conexion.Conexion.getConnection;

import com.itter.estudiantes.dominio.Estudiante;

public class EstudianteDAO {

	public List<Estudiante> listarEstudiantes(){
		List<Estudiante> estudiantes = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Connection con = getConnection();
		String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
		try{
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				var id = resultSet.getInt("id_estudiante");
				var nombre = resultSet.getString("nombre");
				var apellido = resultSet.getString("apellido");
				var telefono = resultSet.getString("telefono");
				var email = resultSet.getString("email");
				var estudiante = new Estudiante(id, nombre, apellido, telefono, email);
				estudiantes.add(estudiante);
			}
		} catch (SQLException e) {
			
			System.out.println("Ocurrió un error al seleccionar los datos: " +e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			
				System.out.println("Ocurrió un error al cerrar la base de datos: " +e.getMessage());
			}
		}
		
		return estudiantes;
	}
	
	//metodo findById
	public boolean buscarEstudiantePorId(Integer id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection con = getConnection();
		String sql = "SELECT * FROM estudiante WHERE id_estudiante = " +id;
		
		try {
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				var nombre = resultSet.getString("nombre");
				var apellido = resultSet.getString("apellido");
				var telefono = resultSet.getString("telefono");
				var email = resultSet.getString("email");
				var estudiante = new Estudiante(id, nombre, apellido, telefono, email);
				System.out.println("Estudiante encontrado con el ID " +id);
				System.out.println(estudiante);
				return true;
			} else
				System.out.println("No se encontró el estudiante con ID " +id);
				return false;
		} catch(SQLException e) {
			System.out.println("Ocurrió un error al consultar los datos: " +e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrió un error al cerrar la base de datos: " +e.getMessage());			}
		}
		return false;
	}
	
	//test
	public static void main(String[] args) {
		var estudianteDao = new EstudianteDAO();
		var id = 3;
		//Listamos los estudiantes
		List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
		estudiantes.forEach(System.out::println);
		estudianteDao.buscarEstudiantePorId(id);
//		if(encontrado) {
//			System.out.println("Esudiante encontrado!");
//			System.out.println("El ID n° " +id+ " corresponde al estudiante : ");
//		} else {
//			System.out.println("No se encuentra un estudiante con el ID " +id);
//		}
//		
	}
	
}
