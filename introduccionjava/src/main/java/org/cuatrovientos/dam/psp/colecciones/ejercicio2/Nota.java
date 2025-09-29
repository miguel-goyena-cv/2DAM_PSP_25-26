package org.cuatrovientos.dam.psp.colecciones.ejercicio2;

public class Nota {

	private String asignatura;
	private double calificacion;
	
	public Nota(String asignatura, double calificacion) {
		super();
		this.asignatura = asignatura;
		this.calificacion = calificacion;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "Nota [asignatura=" + asignatura + ", calificacion=" + Math.round(calificacion * 100.0)/100.0 + "]";
	}
	
}
