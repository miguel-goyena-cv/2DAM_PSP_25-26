package org.cuatrovientos.dam.psp.procesoshilos.procesos;

public class TrabajoPesado {
	
    public static void main(String[] args) {
        System.out.println("Inicio del trabajo pesado...");
        int hilos = Runtime.getRuntime().availableProcessors();
        
        for (int i = 0; i< hilos ; i++) {
        	
        }
        System.out.println("Numero de CPU: " + hilos);
        long contador = 0;
        while (true) { // bucle infinito para consumir CPU
            contador++;
            double x = Math.sin(contador) * Math.cos(contador);
            if (contador % 100_000_000 == 0) {
                System.out.println("Contador: " + contador);
            }
        }
    }

}
