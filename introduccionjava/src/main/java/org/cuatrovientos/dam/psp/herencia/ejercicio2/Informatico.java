package org.cuatrovientos.dam.psp.herencia.ejercicio2;

public class Informatico extends Corredor {

	public Informatico(String nombre) {
		super(nombre);
	}

	@Override
	protected float velocidad() {
		return 3;
	}

}
