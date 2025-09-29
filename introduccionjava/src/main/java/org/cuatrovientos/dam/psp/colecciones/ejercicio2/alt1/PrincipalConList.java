package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class PrincipalConList {

	public static void main(String[] args) {
		
		// Me creo mis notas
		List<NotasAlumno> notasClase = new ArrayList<>();
		rellenarNotas(notasClase);
		
		// Muestro por pantalla
		System.out.println("Lista de Notas: ");
		System.out.println(notasClase.toString());
		System.out.println("======================================");
		
		// Buscar Notas por nombre y Asignatura
		Nota notaAlumnoNombre = buscarNotaPorAlumnoyNombre(notasClase, "Miguel Goyena", "Asignatura0"); // TODO pueden ser MagicNumber
		System.out.println("Existe Nota para Miguel Goyena y la asignatura Asignatura0: ");
		System.out.println(notaAlumnoNombre.toString());
		System.out.println("======================================");
		
		// Eliminar las notas por el nombre
		boolean exito = eliminarNotasDeAlumno(notasClase, "Maria Martin");
		System.out.println("Eliminamos las notas de Maria si existen: "+exito);
		System.out.println(notasClase.toString());
		System.out.println("======================================");

	}



	private static boolean eliminarNotasDeAlumno(List<NotasAlumno> notasClase, String alumno) {
		
		return notasClase.removeIf(notaAlumno -> notaAlumno.getAlumno().equals(alumno));
		
	}


	/**
	 * Busca la Nota por Nombre de Alumno y Asignatura
	 * @param notasClase
	 * @param nombre
	 * @param asignatura
	 * @return La nota, si existe, sino null
	 */
	private static Nota buscarNotaPorAlumnoyNombre(List<NotasAlumno> notasClase, String nombre, String asignatura) {

		for (NotasAlumno notasAlumno: notasClase) {
			if (notasAlumno.getAlumno().equals(nombre)) {
				return notasAlumno.buscarNotaPorAsignatura(asignatura);
			}
		}
		
		return null;
		
	}


	private static void rellenarNotas(List<NotasAlumno> notasClase) {
		NotasAlumno notasAlumno = new NotasAlumno("Miguel Goyena");
		rellenaNotas(notasAlumno);
		notasClase.add(notasAlumno);
		notasAlumno = new NotasAlumno("Maria Martin");
		rellenaNotas(notasAlumno);
		notasClase.add(notasAlumno);
		notasAlumno = new NotasAlumno("Iban Sarria");
		rellenaNotas(notasAlumno);
		notasClase.add(notasAlumno);
		notasAlumno = new NotasAlumno("Silvia Sanz");
		rellenaNotas(notasAlumno);
		notasClase.add(notasAlumno);
	}
	
	private static void rellenaNotas(NotasAlumno notasAlumno) {

		// Semilla para los aleatorios
		Random random = new Random();
		
		// Relleno de una manera Random
		for (int i=0; i < random.nextInt(10) + 1; i++) {
			Nota nota = new Nota("Asignatura"+i, random.nextDouble(9)+1);
			notasAlumno.add(nota);
		}
		
	}

}
