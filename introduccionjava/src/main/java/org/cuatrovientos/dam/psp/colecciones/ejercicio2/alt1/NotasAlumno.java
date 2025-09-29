package org.cuatrovientos.dam.psp.colecciones.ejercicio2.alt1;

import java.util.ArrayList;

import org.cuatrovientos.dam.psp.colecciones.ejercicio2.Nota;

public class NotasAlumno extends ArrayList<Nota> {
	
	private String alumno;

	public NotasAlumno(String alumno) {
		super();
		this.alumno = alumno;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "NotasAlumno [alumno=" + alumno + ", NotaMedia = "+this.calcularNotaMedia()+" Inherited()=" + super.toString() + "]";
	}

	/**
	 * Devuelve la nota por asignatura
	 * @param asignatura
	 * @return Si no hay devuelve nulo
	 */
	public Nota buscarNotaPorAsignatura(String asignatura) {

		for (Nota nota: this) {
			if (nota.getAsignatura().equals(asignatura)) {
				return nota;
			}
		}
		
		// Sino devuelvo Null
		return null;
		
	}
	
	public double calcularNotaMedia() {
		
		double resultado = 0;
		for (Nota nota: this) {
			resultado = resultado + nota.getCalificacion();
		}
		
		return resultado/this.size();
		
	}
	
	

}
