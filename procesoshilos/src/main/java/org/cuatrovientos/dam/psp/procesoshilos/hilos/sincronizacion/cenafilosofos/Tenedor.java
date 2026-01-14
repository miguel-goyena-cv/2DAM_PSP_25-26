package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos;

import java.util.concurrent.Semaphore;

public class Tenedor {
	
	private Integer numero;
	private boolean ocupado;
	
	private Semaphore semaforo = new Semaphore(1);

	public Tenedor(Integer numero) {
		super();
		this.numero = numero;
		this.ocupado = false;
	}
	
	/**
	 * Bloqueo el tenedor y lo marco como ocupado
	 * @throws InterruptedException
	 */
	public void cogerTenedor() throws InterruptedException {
		semaforo.acquire();
		ocupado = true;
	}
	
	/**
	 * Libero el tenedor
	 */
	public synchronized void soltarTenedor() {
		ocupado = false;
		semaforo.release();
	}

	@Override
	public String toString() {
		return "Tenedor [numero=" + numero + ", ocupado=" + ocupado + "]";
	}

}
