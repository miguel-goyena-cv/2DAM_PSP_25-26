package org.cuatrovientos.dam.psp.herencia.ejercicio2;

public class Deportista extends Corredor {

	public Deportista(String nombre) {
		super(nombre);
	}

	@Override
	protected float velocidad() {
		return 7.0f;
	}

}
