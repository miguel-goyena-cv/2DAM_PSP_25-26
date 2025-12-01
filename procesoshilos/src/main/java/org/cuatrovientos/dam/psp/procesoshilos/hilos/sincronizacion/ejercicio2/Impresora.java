package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2;

import java.time.Duration;

public class Impresora implements Runnable {

	// Constantes
	private static final Duration ESPERA_IMPRESORA = Duration.ofMillis(1000);
	private static final int TIEMPO_IMPRIMIR_HOJA_MILLIS = 2000;

	// Variables de clase para identificar a una impresora
	private String nombre;
	
	// Variables de clase utilizadas para manejar la impresorsa
	private ColaImpresion colaImpresion; // Compartida por todos los ordenadores y por todas las impresoras
	private long timestampInicio = 0;
	
	public Impresora(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {

		timestampInicio = System.currentTimeMillis();
		
		try {
			
			// Encendemos la impresora
			boolean impresoraEncendida = true;
			
		
			while (impresoraEncendida) {
			
				// La impresora recoge el trabajo que haya
				Trabajo trabajoAImprimir = this.colaImpresion.recuperarSiguienteTrabajo();
				if (trabajoAImprimir != null) {

					// Simulación de impresion
					logConTimestamp("Se comienza a imprimir: "+trabajoAImprimir);
					Thread.sleep(trabajoAImprimir.getNumeroHojas()*TIEMPO_IMPRIMIR_HOJA_MILLIS);
					logConTimestamp("Se acabo la impresion: "+trabajoAImprimir);
					
					// Acaba la impresion
					this.colaImpresion.finalizandoTrabajo(trabajoAImprimir);
					
				}
				else {
					// Voy a hacer una espera de 1 segundo porque no quiero saturar la CPU de la impresora
					Thread.sleep(ESPERA_IMPRESORA);
				}
				
			}
			
		}
		catch (Exception generalException) {
			logConTimestamp("ERROR Gordo en un hilo de tipo Impresora, interrumpimos el hilo: "+generalException);
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
    	System.out.println("\t\t["+Thread.currentThread().threadId()+"]["+this.nombre+"]; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }

}
