package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio1.atomicinteger;

import java.util.concurrent.Semaphore;

public class HiloCarrera implements Runnable {
	
	private Contador contadorComun;

	public HiloCarrera(Contador contadorComun) {
		super();
		this.contadorComun = contadorComun;
	}

	@Override
	public void run() {

		for (int i = 0; i< 100000; i++) {
			synchronized (contadorComun) {
				this.contadorComun.incrementar();
			}
		}
		
		System.out.println("Acaba hilo: "+Thread.currentThread().threadId()+" y el contador es: "+this.contadorComun.getValor());
		
	}

}
