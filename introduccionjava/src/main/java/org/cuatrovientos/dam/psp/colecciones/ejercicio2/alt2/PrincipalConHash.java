package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class PrincipalConHash {

	public static void main(String[] args) {

		// Me creo mis notas
		NotasAlumnos bbddNotas = new NotasAlumnos();
		bbddNotas.put("Miguel Goyena", rellenaNotas());
		bbddNotas.put("Maria Martin", rellenaNotas());
		bbddNotas.put("Iban Sarria", rellenaNotas());
		bbddNotas.put("Silvia Sanz", rellenaNotas());
		
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
	
	private static List<Nota> rellenaNotas() {

		// Resultado
		List<Nota> resultado = new ArrayList<>();
		
		// Semilla para los aleatorios
		Random random = new Random();
		
		// Relleno de una manera Random
		for (int i=0; i < random.nextInt(10) + 1; i++) {
			Nota nota = new Nota("Asignatura"+i, random.nextDouble(9)+1);
			resultado.add(nota);
		}
		
		return resultado;
		
	}

}
