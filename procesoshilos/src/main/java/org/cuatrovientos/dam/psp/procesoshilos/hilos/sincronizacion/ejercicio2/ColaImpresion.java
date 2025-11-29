package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ColaImpresion{
	
	// Variables de clase para identificar una cola de impresion
	private String nombre;
	private List<Trabajo> trabajosEncolados;
	private List<Trabajo> trabajosEnCurso;
	private List<Trabajo> trabajosFinalizados;
	

	public ColaImpresion(String nombre) {
		super();
		this.nombre = nombre;
		this.trabajosEncolados = new ArrayList<>();
		this.trabajosEnCurso = new ArrayList<>();
		this.trabajosFinalizados = new ArrayList<>();
	}
	
	public void agregarTrabajo(Trabajo trabajo) {
		this.trabajosEncolados.add(trabajo);
	}
	
	public Trabajo comenzarImprimirSiguienteTrabajo() {
		
		// Recupero el trabajo a imprimir
		Trabajo trabajoAImprimir = null;
		try {
			trabajoAImprimir = this.trabajosEncolados.getFirst();
		}
		catch (NoSuchElementException e) {
			return null;
		}
		
		this.trabajosEncolados.remove(trabajoAImprimir);
		this.trabajosEnCurso.add(trabajoAImprimir);
		return trabajoAImprimir;
	}
	
	public void finalizandoTrabajo(Trabajo trabajoImpreso) {
		this.trabajosEnCurso.remove(trabajoImpreso);
		this.trabajosFinalizados.add(trabajoImpreso);
	}

	
	@Override
	public String toString() {
		return "ColaImpresion [nombre=" + nombre + ", trabajosEncolados=" + trabajosEncolados + ", trabajosEnCurso="
				+ trabajosEnCurso + ", trabajosFinalizados=" + trabajosFinalizados + "]";
	}

}
