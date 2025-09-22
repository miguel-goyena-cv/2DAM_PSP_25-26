package org.cuatrovientos.dam.psp.colecciones.ejercicio1;

import java.time.Duration;

public class Receta {
	
	private String nombre;
	private Duration tiempoPreparacion;
	private TipoReceta tipo;
	
	public Receta(String nombre, Duration tiempoPreparacion, TipoReceta tipo) {
		super();
		this.nombre = nombre;
		this.tiempoPreparacion = tiempoPreparacion;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Duration getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Duration tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public TipoReceta getTipo() {
		return tipo;
	}

	public void setTipo(TipoReceta tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Receta [nombre=" + nombre + ", tiempoPreparacion=" + tiempoPreparacion + ", tipo=" + tipo + "]";
	}
	
	
	
}
