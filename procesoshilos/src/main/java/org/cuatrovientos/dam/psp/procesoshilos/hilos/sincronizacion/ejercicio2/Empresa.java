package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2;

import java.time.Duration;

public class Empresa {

	public static void main(String[] args) {

		// Lo genero aqui para poder pararlo en el caso de fallo catastrofico
		Thread hiloImpresora = null;
		Thread hiloOrdenador1 = null;
		Thread hiloOrdenador2 = null;
		Thread hiloOrdenador3 = null;
		
		try {
			//Vamos a crearnos 3 Ordenadores
			Ordenador ordenador1 = new Ordenador("Mikel");
			Ordenador ordenador2 = new Ordenador("Raul");
			Ordenador ordenador3 = new Ordenador("Julian");
			
			// Tenemos una única impresora en la oficina en la Sala1
			Impresora impresora = new Impresora("Sala1");
			
			// Tenemos una unica Cola de Impresion y se la damos a todos los participantes
			ColaImpresion cola = new ColaImpresion("cola1");
			ordenador1.asignarColaImpresion(cola);
			ordenador2.asignarColaImpresion(cola);
			ordenador3.asignarColaImpresion(cola);
			impresora.asignarColaImpresion(cola);
			
			// Al empezar el día arrancamos primero la impresora
			hiloImpresora = arrancarImpresora(impresora);
			
			// Tambien vienen los usuario y van encendiendo los ordenadores
			hiloOrdenador1 = arrancaOrdenador(ordenador1);
			hiloOrdenador2 = arrancaOrdenador(ordenador2);
			hiloOrdenador3 = arrancaOrdenador(ordenador3);
			
			boolean empresaAbierta = true;
			while (empresaAbierta){
				
				Thread.sleep(Duration.ofSeconds(10));
				logConTimestamp(cola.toString());
				
			}
			
		}
		catch(Exception excepcionMain) {
			System.err.println("Excepcion descontrolada en el hilo general: "+excepcionMain.getMessage()+", Cierro el programa principal");
			excepcionMain.printStackTrace();
			cerrarHilosPendientes(hiloImpresora, hiloOrdenador1, hiloOrdenador2, hiloOrdenador3);
		}

	}

	private static Thread arrancaOrdenador(Ordenador ordenador) {
		
		Thread hiloOrdenador = new Thread(ordenador);
		hiloOrdenador.start();
		return hiloOrdenador;
		
	}

	/**
	 * Arranca el hilo de una impresora
	 * @param impresora
	 * @return
	 */
	private static Thread arrancarImpresora(Impresora impresora) {

		Thread hiloImpresora = new Thread(impresora);
		hiloImpresora.start();
		return hiloImpresora;
		
	}
	
	/**
	 * Interrumpe los hilos que queramos
	 * @param hilos
	 */
	private static void cerrarHilosPendientes(Thread... hilos) {
		
		for (Thread hilo: hilos) {
			if (hilo != null && hilo.isAlive()) {
				hilo.interrupt();
			}
		}
		
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private static void logConTimestamp(String log){
    	System.out.println("["+Thread.currentThread().threadId()+"][MAIN]; Timestamp: "+log);
    }

}
