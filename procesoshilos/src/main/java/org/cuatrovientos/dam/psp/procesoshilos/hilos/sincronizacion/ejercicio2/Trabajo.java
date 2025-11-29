package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2;

public class Trabajo {
	
	private String nombre;
	
	private int numeroHojas;

	public Trabajo(String nombre, int numeroHojas) {
		super();
		this.nombre = nombre;
		this.numeroHojas = numeroHojas;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroHojas() {
		return numeroHojas;
	}

	@Override
	public String toString() {
		return "Trabajo [nombre=" + nombre + ", numeroHojas=" + numeroHojas + "]";
	}
	

}
