package org.cuatrovientos.dam.psp.killenemies;

import java.io.Serializable;

public class Heroe implements IPersonaje, Serializable {

	private String nombre;
	private int enemigosEliminados;
	private int amigosDefendidos;
	
	public Heroe(String nombre) {
		super();
		this.nombre = nombre;
		enemigosEliminados = 0;
		amigosDefendidos = 0;
	}

	@Override
	public boolean isEnemy() {
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Heroe [nombre=" + nombre + ", enemigosEliminados=" + enemigosEliminados + ", amigosDefendidos="
				+ amigosDefendidos + "]";
	}

	public void incrementaContadorEnemigos() {
		this.enemigosEliminados++;
	}
	
	public void incrementaContadorAmigos() {
		this.amigosDefendidos++;
	}
	
}
