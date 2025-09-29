package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class NotasAlumnos extends HashMap<String, List<Nota>>{

	/**
	 * Busca la nota ñor alumno y Asignatura
	 * @param alumno
	 * @param asignatura
	 * @return
	 */
	public Nota buscarPorAlumnoYAsignatura(String alumno, String asignatura) {
		
		Nota notaEncontrada = null; // Inicializo a nulo para que si no encuentra nada, me devuelva un nulo
		
		if (this.containsKey(alumno)) {
			List<Nota> notasAlumno = this.get(alumno);
			// Buscamos por asignatura
			for (Nota nota: notasAlumno) {
				if (nota.getAsignatura().equals(asignatura)) {
					notaEncontrada = nota;
					break;
				}
			}
		}
		
		// En otro caso devuelvo nulo
		return notaEncontrada;
	}

	@Override
	public String toString() {
		
		String representacionString = "";
		
		for (Map.Entry<String, List<Nota>> entry : this.entrySet()) {
		    String clave = entry.getKey();
		    List<Nota> valor = entry.getValue();
		    representacionString = representacionString + "Alumno = "+clave+"\n";
		    for (Nota nota: valor) {
		    	representacionString = representacionString + "\t"+ "Asignatura: "+nota.getAsignatura()+", Calificacion: "+nota.getCalificacion() + "\n";
		    }
		    representacionString = representacionString + "===============================================\n";
		}
		
		return representacionString;
	}
	
	
}
