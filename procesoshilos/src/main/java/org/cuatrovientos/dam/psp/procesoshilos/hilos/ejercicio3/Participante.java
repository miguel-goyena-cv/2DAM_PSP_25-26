package org.cuatrovientos.dam.psp.procesoshilos.hilos.ejercicio3;

import java.util.Random;

public class Participante implements Runnable {

	private String nombre;
	private int longitud;
	private int contador;
	private Random random = new Random();
	private long timestampInicio;
	
	public Participante(String nombre, int longitud) {
		super();
		this.longitud = longitud;
		this.nombre = nombre;
		this.contador = 0;
	}

	@Override
	public void run() {

		timestampInicio = System.currentTimeMillis();
		
		// Al llegar a la longitud se acaba la carrera
		while (contador < longitud) {
			try {
				Thread.sleep(random.nextInt(901) + 100);
				contador++;
				logConTimestamp("Avanzamos- "+this.contador);
			} catch (InterruptedException e) {
				logConTimestamp("Error en participante, para de correr al instante");
			}
		}
		
		logConTimestamp("Finaliza la carrera");

	}
	
	
    public String getNombre() {
		return nombre;
	}

	public int getContador() {
		return contador;
	}

	/**
	 * Frenar al partipante es quitarle 2 al contador
	 */
	public void frenar() {
		logConTimestamp("Frenamos que vamos primeros!!!");
		contador = contador -2;
		
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	System.out.println("Hilo: "+nombre+"; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }

	@Override
	public String toString() {
		return "Participante [nombre=" + nombre + ", contador=" + contador + "]";
	}

}
