package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Profesor {

	public void finalizoExamen(Set<Alumno> alumnos) {
		
		for (Alumno alumnoAFinalizar: alumnos) {
			alumnoAFinalizar.irFinalizando();
		}
		
		
	}

	public String recuperoInformacionExamenEnCurso(Map<Alumno, Thread> hilosAlumno) {

		String resultado = "";
		
		for (Map.Entry<Alumno, Thread> alumno : hilosAlumno.entrySet()) {
			resultado += alumno.getKey().printInfo();
		}
		
		return resultado;
		
	}

	public void dejarAlumnoBano(Alumno alumno) {
		
		alumno.irAlBano();
		
	}

	public void expulsarAlumnno(Entry<Alumno, Thread> alumnoAExpulsar) {

		// Corto el hilo de ejecucion
		if (alumnoAExpulsar.getValue().isAlive())
			alumnoAExpulsar.getValue().interrupt();
		
		// Le suspendo
		alumnoAExpulsar.getKey().suspender();
		
		System.out.println("Suspendo al alumno: "+alumnoAExpulsar.getKey().getNombre()+" por copiar");
		
	}

	/**
	 * Añado la pregunta a todos los examenes de los alumnos.
	 * @param hilosAlumno
	 * @param preguntaNueva
	 */
	public void anadirPregunta(Map<Alumno, Thread> hilosAlumno, Pregunta preguntaNueva) {

		// Para todos los alumnos cambio el examen
		for (Alumno alumno: hilosAlumno.keySet()) {
			alumno.getExamen().setPreguntaExtra(preguntaNueva);
		}
		
	}
	
	

}
