package org.cuatrovientos.dam.psp.herencia.ejercicio2;

public abstract class Corredor {
	
	private String nombre;

	public Corredor(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	/**
	 * MUY IMPORTANTE: Hay que comentar los metodos abstractos.
	 * @return La velocidad en la que corre ese corredor.
	 */
	protected abstract float velocidad();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Corredor [nombre=" + nombre + "]";
	}
	
	

}
