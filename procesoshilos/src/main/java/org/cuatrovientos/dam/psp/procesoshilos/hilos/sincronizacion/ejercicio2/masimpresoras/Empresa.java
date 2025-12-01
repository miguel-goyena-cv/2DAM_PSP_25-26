package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio2.masimpresoras;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Empresa {

	public static void main(String[] args) {

		// Lo genero aqui para poder pararlo en el caso de fallo catastrofico
		List<Thread> hilosImpresoras = new ArrayList<>();
		List<Thread> hilosOrdenadores = new ArrayList<>();
		
		try {
			//Vamos a crearnos 3 Ordenadores
			Ordenador ordenador1 = new Ordenador("Mikel");
			Ordenador ordenador2 = new Ordenador("Raul");
			Ordenador ordenador3 = new Ordenador("Julian");
			
			// Tenemos 3 impresoras, 2 en color y 1 en B/N
			Impresora impresora1 = new Impresora("Sala1_Color");
			Impresora impresora2 = new Impresora("Sala2_Color");
			Impresora impresora3 = new Impresora("Sala3_BN");
			
			// Tenemos una cola de impresión para B/N y otra para color y se la damos a todos los participantes
			ColaImpresion colaBN = new ColaImpresion("BN");
			ColaImpresion colaColor = new ColaImpresion("Color");
			ordenador1.asignarColaImpresion(colaBN, colaColor);
			ordenador2.asignarColaImpresion(colaBN, colaColor);
			ordenador3.asignarColaImpresion(colaBN, colaColor);
			impresora1.asignarColaImpresion(colaColor);
			impresora2.asignarColaImpresion(colaColor);
			impresora3.asignarColaImpresion(colaBN);
			
			// Al empezar el día arrancamos primero la impresora
			hilosImpresoras = arrancarImpresoras(impresora1, impresora2, impresora3);
			
			// Tambien vienen los usuario y van encendiendo los ordenadores
			hilosOrdenadores = arrancaOrdenadores(ordenador1, ordenador2, ordenador3);
			
			boolean empresaAbierta = true;
			while (empresaAbierta){
				
				Thread.sleep(Duration.ofSeconds(10));
				logConTimestamp(colaBN.toString());
				logConTimestamp(colaColor.toString());
				
			}
			
		}
		catch(Exception excepcionMain) {
			System.err.println("Excepcion descontrolada en el hilo general: "+excepcionMain.getMessage()+", Cierro el programa principal");
			excepcionMain.printStackTrace();
			cerrarHilosPendientes(Stream.concat(hilosImpresoras.stream(), hilosOrdenadores.stream())
                    .collect(Collectors.toList()));
		}

	}

	/**
	 * Arranca todos los hilos de los ordenadores
	 * @param ordenadores
	 * @return
	 */
	private static List<Thread> arrancaOrdenadores(Ordenador... ordenadores) {
		
		List<Thread> hilosOrdenadores = new ArrayList<>();
		
		for (Ordenador hilo: ordenadores) {
			Thread hiloOrdenador = new Thread(hilo);
			hiloOrdenador.start();
			hilosOrdenadores.add(hiloOrdenador);
		}

		return hilosOrdenadores;
		
	}

	/**
	 * Arranca todos los hilos de las impresoras
	 * @param impresoras
	 * @return
	 */
	private static List<Thread> arrancarImpresoras(Impresora... impresoras) {

		List<Thread> hilosImpresoras = new ArrayList<>();
		
		for (Impresora hilo: impresoras) {
			Thread hiloImpresora = new Thread(hilo);
			hiloImpresora.start();
			hilosImpresoras.add(hiloImpresora);
		}

		return hilosImpresoras;
		
	}
	
	/**
	 * Interrumpe los hilos que queramos
	 * @param hilos
	 */
	private static void cerrarHilosPendientes(List<Thread> hilos) {
		
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
