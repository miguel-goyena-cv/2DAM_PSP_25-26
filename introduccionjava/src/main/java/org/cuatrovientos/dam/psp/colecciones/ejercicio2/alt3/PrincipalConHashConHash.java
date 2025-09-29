package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt3;

import java.util.Arrays;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class PrincipalConHashConHash {

	public static void main(String[] args) {

		// Me creo mis notas
		NotasAlumnos bbddNotas = new NotasAlumnos();
		bbddNotas.rellenarNotas(Arrays.asList("Miguel Goyena", "Maria Martin", "Iban Sarria", "Silvia Sanz"));
		
		// Muestro por pantalla
		System.out.println("Lista de Notas: ");
		System.out.println(bbddNotas.toString());
		System.out.println("======================================");
		
		// Buscar Notas por nombre y Asignatura
		Nota notaAlumnoNombre = bbddNotas.buscarPorAlumnoYAsignatura("Miguel Goyena", "Asignatura0");
		System.out.println("Existe Nota para Miguel Goyena y la asignatura Asignatura0: ");
		System.out.println(notaAlumnoNombre.toString());
		System.out.println("======================================");
		
		// Eliminar las notas por el nombre
		boolean exito = (bbddNotas.remove("Maria Martin") != null);
		System.out.println("Eliminamos las notas de Maria si existen: "+exito);
		System.out.println(bbddNotas.toString());
		System.out.println("======================================");

	}
	

}
