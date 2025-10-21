package org.cuatrovientos.dam.psp.procesoshilos.hilos;

public class TrabajoPesadoLanzaHilos {
	
    public static void main(String[] args) throws InterruptedException {
    	
    	
    	// Inicializo con los parametros
    	int numHilos = 8000000;
    	if (args.length > 0) {
    		numHilos = Integer.parseInt(args[0]);
    	}
//    	if (numHilos > Runtime.getRuntime().availableProcessors()) {
//    		numHilos = Runtime.getRuntime().availableProcessors();
//    	}
        System.out.println("Hilos a crear: " + numHilos);
        

        // Crear y lanzar Hilos
        Thread[] hilos = new Thread[numHilos];
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new TrabajoPesadoHilo(i + 1));
            hilos[i].start();
        }
        

        // Esperar a que terminen todos
        for (Thread hilo : hilos) {
            hilo.join();
        }
        

        System.out.println("Todas las tareas han finalizado.");
    }

}
