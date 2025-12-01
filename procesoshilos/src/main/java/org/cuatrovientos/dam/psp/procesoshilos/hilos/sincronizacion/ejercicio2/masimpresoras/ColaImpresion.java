package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2.masimpresoras;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ColaImpresion{
	
	// Variables de clase para identificar una cola de impresion
	private String nombre;
	private List<Trabajo> trabajosRecibidos;
	private List<Trabajo> trabajosEncolados;
	private List<Trabajo> trabajosEnCurso;
	private List<Trabajo> trabajosFinalizados;
	

	public ColaImpresion(String nombre) {
		super();
		this.nombre = nombre;
		this.trabajosRecibidos = new ArrayList<>();
		this.trabajosEncolados = new ArrayList<>();
		this.trabajosEnCurso = new ArrayList<>();
		this.trabajosFinalizados = new ArrayList<>();
	}
	
	public synchronized void agregarTrabajo(Trabajo trabajo) throws InterruptedException {
		logConTimestamp("La cola recibe un trabajo de impresion: "+trabajo.getNombre());
		this.trabajosRecibidos.add(trabajo);
		this.trabajosEncolados.add(trabajo);
	}
	
	public synchronized Trabajo recuperarSiguienteTrabajo() throws InterruptedException {
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
		logConTimestamp("La cola entrega un trabajo de impresion: "+trabajoAImprimir.getNombre());
		return trabajoAImprimir;
	}
	
	public synchronized void finalizandoTrabajo(Trabajo trabajoImpreso) {
		logConTimestamp("La cola va a dar por finalizado un trabajo: "+trabajoImpreso.getNombre());
		this.trabajosEnCurso.remove(trabajoImpreso);
		this.trabajosFinalizados.add(trabajoImpreso);
		
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	System.err.println("["+Thread.currentThread().threadId()+"][" + this.nombre + "]; Log: "+log);
    }

	@Override
	public String toString() {
		return "ColaImpresion [nombre=" + nombre + ", trabajosRecibidos=" + trabajosRecibidos + ", trabajosEncolados="
				+ trabajosEncolados + ", trabajosEnCurso=" + trabajosEnCurso + ", trabajosFinalizados="
				+ trabajosFinalizados + "]"+ "Se ha mantenido el orden: "+(this.trabajosRecibidos.equals(this.trabajosFinalizados));
	}

	


}
