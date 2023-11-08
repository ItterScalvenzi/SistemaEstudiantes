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
	
	public void agregarEstudiante(Estudiante estudiante) {
		PreparedStatement statement;
		//ResultSet resultSet;
		Connection con = getConnection();
		String sql = "INSERT INTO estudiante (nombre, apellido, telefono, email) VALUES (?,?,?,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, estudiante.getNombre());
			statement.setString(2, estudiante.getApellido());
			statement.setString(3, estudiante.getTelefono());
			statement.setString(4, estudiante.getTelefono());
			int agregados = statement.executeUpdate();
			//resultSet = statement.getGeneratedKeys();
			 if (agregados >0){
				System.out.println("Estudiante agregado!");
				System.out.println(estudiante);
			} else {
				System.out.println("No se pudo agregar al estudiante");
			}
		} catch (SQLException e) {
			System.out.println("Ocurrió un error al agregar los datos: " +e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión : " +e.getMessage());;
			}
		}
	}
	
	public void modificarEstudiante(Estudiante estudiante) {
		PreparedStatement statement;
		
		Connection con = getConnection();
		String sql = "UPDATE estudiante SET nombre =?, apellido=?, telefono= ?, email= ?"
				+ " WHERE id_estudiante = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, estudiante.getNombre());
			statement.setString(2, estudiante.getApellido());
			statement.setString(3, estudiante.getTelefono());
			statement.setString(4, estudiante.getTelefono());
			statement.setInt(5, estudiante.getIdEstudiante());
			var modificados = statement.executeUpdate();
			if (modificados >0){
				System.out.println("Estudiante modificado!");
				System.out.println(estudiante);
			} else {
				System.out.println("No se pudo modficar al estudiante");
			}
			
		} catch (SQLException e) {
			System.out.println("Ocurrió un error al modificar los datos: " +e.getMessage());
		}
	}
	
	//test
	public static void main(String[] args) {
		var estudianteDao = new EstudianteDAO();
		//var id = 3;
		//Listamos los estudiantes
		System.out.println("Listado de Estudiantes");
		List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
		estudiantes.forEach(System.out::println);
		//estudianteDao.buscarEstudiantePorId(id);
		//var estudianteNuevo = new Estudiante("Gianluca", "Scalvenzi", "4889632", "gian@gmail.com");
		//estudianteDao.agregarEstudiante(estudianteNuevo);
		var estudianteModificado = 
				new Estudiante(3,"Morena", "Scalvenzi", "4632353", "more@gmail.com");
		estudianteDao.modificarEstudiante(estudianteModificado);
		System.out.println("Listado de Estudiantes");
		estudiantes = estudianteDao.listarEstudiantes();
		estudiantes.forEach(System.out::println);
	}
	
}
