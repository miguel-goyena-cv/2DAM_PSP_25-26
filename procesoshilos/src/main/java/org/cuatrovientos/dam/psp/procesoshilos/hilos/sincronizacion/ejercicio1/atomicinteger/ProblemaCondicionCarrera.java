package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio1.atomicinteger;

import java.util.concurrent.Semaphore;

public class ProblemaCondicionCarrera {
	
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Empiezo mi programa");
			
			Contador contadorComun = new Contador(); //Contador que utilizan los 2 hilos.
			
			Thread hilo1 = new Thread(new HiloCarrera(contadorComun));
			Thread hilo2 = new Thread(new HiloCarrera(contadorComun));
			
			hilo1.start();
			hilo2.start();
			
			hilo1.join();
			hilo2.join();
			
			System.out.println("Acaba la carrera y el contador es: "+contadorComun.getValor());
			
		}
		catch (Exception e) {
			System.err.println("Exception en el Main principal: "+e.getMessage());
			e.printStackTrace();
		}

	}

}
