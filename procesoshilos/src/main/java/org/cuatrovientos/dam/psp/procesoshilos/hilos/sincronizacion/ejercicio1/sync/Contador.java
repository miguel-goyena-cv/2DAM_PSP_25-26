package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.ejercicio1.sync;

import java.util.concurrent.atomic.AtomicInteger;

public class Contador {
	
    private AtomicInteger contador = new AtomicInteger(0);

    public void incrementar() {
        contador.incrementAndGet();
    }

    public int getValor() {
        return contador.get();
    }
	

}
