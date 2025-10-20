package org.cuatrovientos.dam.psp.procesoshilos.procesos;

import java.util.Arrays;

public class EjemploLanzarNotepad {

	public static void main(String[] args) {

		try {
			
			// Creo una instancia del ProcessBuilder
			System.out.println("Creo instancia del ProcessBuilder");
			ProcessBuilder lanzadorProcesoNotepad = new ProcessBuilder(Arrays.asList("Notepad.exe", "hola.txt"));
			
			// Lanzo el proceso
			System.out.println("Ahora lanzo el proceso");
			Process procesoEjecutandose = lanzadorProcesoNotepad.start();
			System.out.println("Proceso lanzado con el PID: "+procesoEjecutandose.pid());
			
			// Espero a que termine el proceso
			int exitValue = procesoEjecutandose.waitFor();
			System.out.println("Acabo el proceso con exitValue: "+exitValue);
		
			// FInalización del programa
			System.out.println("Finalizo el programa");
			System.exit(200);
			
			
		}
		catch (Exception e) {
			System.err.println("Error en el programa principal: "+e.getMessage());
			e.printStackTrace();
		}
		

	}

}
