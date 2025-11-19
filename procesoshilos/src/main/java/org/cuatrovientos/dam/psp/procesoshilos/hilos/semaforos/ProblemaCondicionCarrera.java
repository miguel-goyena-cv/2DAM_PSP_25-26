package org.cuatrovientos.dam.psp.procesoshilos.hilos.semaforos;

import java.util.concurrent.Semaphore;

public class ProblemaCondicionCarrera {
	
	private static final int NIVEL_CONCURRENCIA = 1;
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Empiezo mi programa");
			
			Contador contadorComun = new Contador(); //Contador que utilizan los 2 hilos.
			Semaphore semaforo = new Semaphore(NIVEL_CONCURRENCIA);
			
			Thread hilo1 = new Thread(new HiloCarrera(contadorComun, semaforo));
			Thread hilo2 = new Thread(new HiloCarrera(contadorComun, semaforo));
			
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
