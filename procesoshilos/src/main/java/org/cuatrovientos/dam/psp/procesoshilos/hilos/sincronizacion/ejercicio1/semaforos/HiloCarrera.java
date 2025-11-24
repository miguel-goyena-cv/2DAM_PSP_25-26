package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio1.semaforos;

import java.util.concurrent.Semaphore;

public class HiloCarrera implements Runnable {
	
	private Contador contadorComun;
	private Semaphore semaforoContador;

	public HiloCarrera(Contador contadorComun, Semaphore semaforo) {
		super();
		this.contadorComun = contadorComun;
		this.semaforoContador = semaforo;
	}

	@Override
	public void run() {

		for (int i = 0; i< 100000; i++) {
			try {
				this.semaforoContador.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.contadorComun.incrementar();
			this.semaforoContador.release();
		}
		
		System.out.println("Acaba hilo: "+Thread.currentThread().threadId()+" y el contador es: "+this.contadorComun.getValor());
		
	}

}
