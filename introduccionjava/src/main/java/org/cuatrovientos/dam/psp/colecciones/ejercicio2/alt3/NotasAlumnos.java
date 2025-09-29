package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class NotasAlumnos extends HashMap<String, Map<String,Nota>>{

	/**
	 * Busca la nota ñor alumno y Asignatura
	 * @param alumno
	 * @param asignatura
	 * @return
	 */
	public Nota buscarPorAlumnoYAsignatura(String alumno, String asignatura) {
		
		Nota notaEncontrada = null; // Inicializo a nulo para que si no encuentra nada, me devuelva un nulo
		
		// Utilizo el containsKey y get para buscar en mapas
		if (this.containsKey(alumno)) {
			Map<String,Nota> notasAlumno = this.get(alumno);
			if (notasAlumno.containsKey(asignatura)) {
				notaEncontrada = notasAlumno.get(asignatura);
			}
		}
		
		// En otro caso devuelvo nulo
		return notaEncontrada;
	}

	@Override
	public String toString() {
		
		if (this.isEmpty())
			return "BBDD vacio";
		
		String representacionString = "";
		
		for (Map.Entry<String, Map<String,Nota>> entry : this.entrySet()) {
		    String clave = entry.getKey();
		    Map<String,Nota> valor = entry.getValue();
		    representacionString = representacionString + "Alumno = "+clave+"\n";
		    for (String asignatura: valor.keySet()) {
		    	Nota nota = valor.get(asignatura);
		    	representacionString = representacionString + "\t"+ "Asignatura: "+nota.getAsignatura()+", Calificacion: "+Math.round(nota.getCalificacion() * 100.0)/100.0  + "\n";
		    }
		    representacionString = representacionString + "\t"+ "NotaMedia: "+ this.calcularNotaMedia(clave)  + "\n";
		    representacionString = representacionString + "===============================================\n";
		}
		
		return representacionString;
	}


	/**
	 * Crea las notas de los alumnos pasados por parámetros
	 * @param listaAlumnos
	 */
	public void rellenarNotas(List<String> listaAlumnos) {
		
		for (String nombre : listaAlumnos) {
			this.put(nombre, rellenaNotas());
		}
		
	}
	
	/**
	 * Rellena una lista de notas para un alumno de forma aleatoria
	 * @return
	 */
	private Map<String, Nota> rellenaNotas() {

		// Resultado
		Map<String, Nota> resultado = new HashMap<>();
		
		// Semilla para los aleatorios
		Random random = new Random();
		
		// Relleno de una manera Random
		for (int i=0; i < random.nextInt(10) + 1; i++) {
			Nota nota = new Nota("Asignatura"+i, random.nextDouble(9)+1);
			resultado.put("Asignatura"+i, nota);
		}
		
		return resultado;
		
	}
	
	/**
	 * Calcula la nota media de un alumno en concreto
	 */
	private Double calcularNotaMedia(String alumno) {
		
		// En el caso en que no existe el alumno o que no tenga notas devuelvo nulo
		if (!this.containsKey(alumno) || this.get(alumno).isEmpty()) {
			return null;
		}
		
		// Caculo la media de sus notas
		Map<String, Nota> notasAlumno = this.get(alumno);
		double acumulado = 0;
	    for (String asignatura: notasAlumno.keySet()) {
	    	Nota nota = notasAlumno.get(asignatura);
	    	acumulado = acumulado + nota.getCalificacion();
	    }
	    return acumulado/notasAlumno.size();
		    
	}
	
	
}
