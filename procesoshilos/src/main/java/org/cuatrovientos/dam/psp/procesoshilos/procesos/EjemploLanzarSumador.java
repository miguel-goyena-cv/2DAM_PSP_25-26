package org.cuatrovientos.dam.psp.procesoshilos.procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EjemploLanzarSumador {

	public static void main(String[] args) {

		String claseSumador = "org.cuatrovientos.dam.psp.procesoshilos.procesos.Sumador";
		
		try {
			
			// Creo una instancia del ProcessBuilder
			System.out.println("Creo instancia del ProcessBuilder");
			ProcessBuilder lanzadorProcesoSuma = new ProcessBuilder(Arrays.asList("java", claseSumador, "1", "2"));
			lanzadorProcesoSuma.directory(new File(".\\target\\classes"));
			//lanzadorProcesoSuma.redirectOutput(new File("Resultado.txt"));
			
			// Lanzo el proceso
			System.out.println("Ahora lanzo el proceso");
			Process procesoEjecutandose = lanzadorProcesoSuma.start();
			System.out.println("Proceso lanzado con el PID: "+procesoEjecutandose.pid());
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(procesoEjecutandose.getInputStream()));
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println("Sumador devuelve: "+linea);
			}
			
			// FInalización del programa
			int exitValue = procesoEjecutandose.waitFor();
			System.out.println("Ha finalizado el programa sumador: "+exitValue);
			
			
		}
		catch (Exception e) {
			System.err.println("Error en el programa principal: "+e.getMessage());
			e.printStackTrace();
		}
		

	}

}
