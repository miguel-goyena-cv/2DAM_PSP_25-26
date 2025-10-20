package org.cuatrovientos.dam.psp.procesoshilos.procesos;

import java.io.File;
import java.util.Arrays;

public class EjemploLanzarTrabajoPesado {

	public static void main(String[] args) {

		String claseTrabajoPesado = "org.cuatrovientos.dam.psp.procesoshilos.procesos.TrabajoPesado";
		
		try {
			
			// Creo una instancia del ProcessBuilder
			System.out.println("Creo instancia del ProcessBuilder");
			ProcessBuilder lanzadorProcesoSuma = new ProcessBuilder(Arrays.asList("java", claseTrabajoPesado));
			lanzadorProcesoSuma.directory(new File(".\\target\\classes"));
			
			
	        int hilos = Runtime.getRuntime().availableProcessors();
	        hilos = 4;
	        
	        for (int i = 0; i< hilos ; i++) {
	    		System.out.println("Ahora lanzo el proceso");
				Process procesoEjecutandose = lanzadorProcesoSuma.start();
	        }
			
			
		}
		catch (Exception e) {
			System.err.println("Error en el programa principal: "+e.getMessage());
			e.printStackTrace();
		}
		

	}

}
