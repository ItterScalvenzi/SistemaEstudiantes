package com.itter.estudiantes.vista;

import java.util.List;
import java.util.Scanner;

import com.itter.estudiantes.dao.EstudianteDAO;
import com.itter.estudiantes.dominio.Estudiante;


public class SistemaEstudiantesApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		var salir = false;
		while (!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOpciones(sc);
			} catch (Exception e) {
				System.out.println("Ocurrió un error: " + e.getMessage());
			}
		}
	}

	private static void mostrarMenu() {
		System.out.println();
		System.out.println("***Sistema de Estudiantes***");
		System.out.println("");
		System.out.println("""
				1. Listar Estudiantes
				2. Buscar Estudiantes
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				""");

	}

	private static boolean ejecutarOpciones(Scanner sc) {
		
		var estudianteDao = new EstudianteDAO();
		List<Estudiante> estudiantes;
		var salir = false;
		System.out.println("Ingrese una opción: ");
		var opcion = Integer.parseInt(sc.nextLine());
		switch (opcion) {
		
		case 1 -> {
			System.out.println("\t\t\t--------LISTADO DE ESTUDIANTES--------");
			System.out.println("");
			System.out.println("\tId \tNombre \t\tApellido \tTelefono \tEmail");
			estudiantes = estudianteDao.listarEstudiantes();
			estudiantes.forEach(System.out::println);
			break;
		}
		case 2 -> {
			System.out.println("\t\t\t---Buscar Estudiante---");
			System.out.println("");
			System.out.println("Ingrese el id del Estudiante: ");
			var id = Integer.parseInt(sc.nextLine());
			System.out.println("");
			estudianteDao.buscarEstudiantePorId(id);
			break;
		}
		case 3 -> {
			System.out.println("\t\t\t---NUEVO ESTUDIANTE---");
			System.out.println("");
			System.out.println("Ingrese el nombre: ");
			var nombre = sc.nextLine();
			System.out.println("Ingrese el apellido: ");
			var apellido = sc.nextLine();
			System.out.println("Ingrese el teléfono: ");
			var telefono = sc.nextLine();
			System.out.println("Ingrese el email: ");
			var email = sc.nextLine();
			var estudianteNuevo = new Estudiante(nombre, apellido, telefono, email);
			System.out.println("");
			estudianteDao.agregarEstudiante(estudianteNuevo);
			break;
		}
		case 4 -> {
			System.out.println("\t\t\t---MODIFICANDO ESTUDIANTE---");
			System.out.println("");
			System.out.println("Ingrese el id del estudiante: ");
			var id = Integer.parseInt(sc.nextLine());
			if(estudianteDao.buscarEstudiantePorId(id)) {
				System.out.println("Ingrese el nombre: ");
				var nombre = sc.nextLine();
				System.out.println("Ingrese el apellido: ");
				var apellido = sc.nextLine();
				System.out.println("Ingrese el teléfono: ");
				var telefono = sc.nextLine();
				System.out.println("Ingrese el email: ");
				var email = sc.nextLine();
				var estudianteModificado = 
						new Estudiante(id, nombre, apellido, telefono, email);
				System.out.println("");
				estudianteDao.modificarEstudiante(estudianteModificado);
			} else {
				System.out.println("No se encuentra el estudiante con ID: " +id);
			}
			break;
		}
		case 5 ->{
			System.out.println("\t\t\t---ELIMINAR ESTUDIANTE---");
			System.out.println("");
			System.out.println("Ingrese el id del estudiante: ");
			var id = Integer.parseInt(sc.nextLine());
			System.out.println("");
			estudianteDao.eliminarEstudiante(id);
			break;
		}
		case 6 -> {
			System.out.println("Hasta pronto...");
			salir = true;
			break;
		}
		default -> System.out.println("Ingrese una opción valida");
		}
		return salir;
	}
}
