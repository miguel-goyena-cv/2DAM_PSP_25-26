package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2;

import java.time.Duration;
import java.util.Random;

public class Ordenador implements Runnable {
	
	// Constantes de clase
	private static final int NUMERO_MAXIMO_HOJAS_TRABAJO = 20;
	private static final long MAXIMO_TIEMPO_ENTRE_TRABAJOS_MILLIS = Duration.ofSeconds(60).getSeconds()*1000;
	private static final long MINIMO_TIEMPO_ENTRE_TRABAJOS_MILIS = Duration.ofSeconds(30).getSeconds()*1000;

	// Variables de clase para identificar a un ordenador
	private String usuarioIdentificado;
	
	// Variables de clase utilizadas para manejar el ordenador
	private ColaImpresion colaImpresion; // Compartida por todos los ordenadores y por todas las impresoras
	private Random rand = new Random();
	private long timestampInicio = 0;

	public Ordenador(String usuarioIdentificado) {
		super();
		this.usuarioIdentificado = usuarioIdentificado;
	}
	
	@Override
	public void run() {
		
		timestampInicio = System.currentTimeMillis();
		
		try {
			
			// Encendemos el ordenador
			boolean ordenadorEncendido = true;
			int numeroTrabajo = 1; // Para poder dar un nombre al trabajo
			
			
			while (ordenadorEncendido) {
			
				// El trabajo aleatoriamente es de 1-20 hojas
				String nombreTrabajo = "Trabajo_"+usuarioIdentificado+"_"+numeroTrabajo++;
				Trabajo trabajoImprimir = new Trabajo(nombreTrabajo, rand.nextInt(NUMERO_MAXIMO_HOJAS_TRABAJO-1)+1);
				
				// Mando a imprimir
				logConTimestamp("Se manda a imprimir: "+trabajoImprimir);
				colaImpresion.agregarTrabajo(trabajoImprimir);
				
				// Simulamos que cada 30-60 segundos mandamos un trabajo a imprimir
				Thread.sleep(rand.nextLong(MINIMO_TIEMPO_ENTRE_TRABAJOS_MILIS)+(MAXIMO_TIEMPO_ENTRE_TRABAJOS_MILLIS-MINIMO_TIEMPO_ENTRE_TRABAJOS_MILIS));
				
			}
			
		}
		catch (Exception generalException) {
			logConTimestamp("ERROR Gordo en un hilo de tipo Ordenador, interrumpimos el hilo: "+generalException);
			Thread.currentThread().interrupt();
		}
		
	}
	
	public void asignarColaImpresion(ColaImpresion cola) {
		this.colaImpresion = cola;
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	System.out.println("\t["+Thread.currentThread().threadId()+"]["+this.usuarioIdentificado+"]; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }
	
	

}
