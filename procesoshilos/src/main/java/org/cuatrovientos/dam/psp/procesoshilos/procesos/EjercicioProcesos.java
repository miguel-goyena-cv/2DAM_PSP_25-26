package org.cuatrovientos.dam.psp.procesoshilos.procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class EjercicioProcesos {
	
	private static final String NOMBRE_FICHERO_OPERANDO1 = "operando1.txt";
	private static final String NOMBRE_FICHERO_OPERANDO2 = "operando2.txt";
	private static final String EDITOR_TEXTO = "Notepad.exe";
	private static final String SUMADOR = "org.cuatrovientos.dam.psp.procesoshilos.procesos.Sumador";
	

	public static void main(String[] args) {
		
		Scanner scanner = null;
		boolean continuar = true;

		// Cualquier error no está controlado y finalizará el programa
		try {

			// Bucle para realizar mas operaciones
			while (continuar) {
				
				// Creo los procesos para los operando
				Process procesoOperando1 = crearProcesoOperando(NOMBRE_FICHERO_OPERANDO1);
				Process procesoOperando2 = crearProcesoOperando(NOMBRE_FICHERO_OPERANDO2);
				
				// Espero a que acaben ambos procesos
				int exitValueOperando1 = procesoOperando1.waitFor();
				int exitValueOperando2 = procesoOperando2.waitFor();
				
				// Ahora tengo que leer los resultados
				double operando1 = leerOperando(NOMBRE_FICHERO_OPERANDO1);
				double operando2 = leerOperando(NOMBRE_FICHERO_OPERANDO2);
				
				// Lanzo el proceso de sumador
				Process procesoSumador = lanzarProcesoSumador(operando1, operando2);
				double resultado = recogerResultadoSuma(procesoSumador);
				System.out.println("El resultado de la suma es: "+resultado);
				
				// Miro si quiere otra operacion
				scanner = new Scanner(System.in);
	            System.out.print("Quiere realizar otra operación (S/n): ");
	            String respuesta = scanner.nextLine();
	            continuar = (respuesta.equalsIgnoreCase("s"));
	            
			}
			System.out.print("Fin del programa");

		} catch (Exception e) {
			System.err.println("Error en el programa principal: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	/**
	 * Crea un proceso de Notepad para escribir un operando
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private static Process crearProcesoOperando(String fileName) throws IOException {
		
		// Creo una instancia del ProcessBuilder para un proceso de Notepad
		System.out.println("Creo instancia del ProcessBuilder para lanzar Notepad con: "+fileName);
		ProcessBuilder lanzadorProcesoNotepad = new ProcessBuilder(Arrays.asList(EDITOR_TEXTO, fileName));
		
		
		// Lanzo el proceso
		System.out.println("Ahora lanzo el proceso... para escribir en: "+fileName);
		Process procesoEjecutandose = lanzadorProcesoNotepad.start();
		System.out.println("Proceso lanzado con el PID: " + procesoEjecutandose.pid());
		
		return procesoEjecutandose;
		
	}
	
	/**
	 * Lee un double a partir de una ruta de fichero
	 * @param nombreFichero
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private static double leerOperando(String nombreFichero) throws IOException, ParseException {
		
		BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
        String primeraLinea = br.readLine(); // Lee solo la primera línea
        
        return convertirLineaDouble(primeraLinea);
		
	}
	
	/**
	 * Convierte un String en un double en formato Locale.US
	 * @param linea
	 * @return
	 * @throws ParseException
	 */
	private static double convertirLineaDouble(String linea) throws ParseException {
		NumberFormat formato = NumberFormat.getInstance(Locale.US); // Separador de .
        return formato.parse(linea).doubleValue();
	}
	

	/**
	 * Recoge el resultado de la suma
	 * @param procesoSumador
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private static double recogerResultadoSuma(Process procesoSumador) throws IOException, ParseException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(procesoSumador.getInputStream()));
		String linea = reader.readLine();
		
        return convertirLineaDouble(linea);
	}

	/**
	 * Lanza el proceso de sumar 2 numeros
	 * @param operando1
	 * @param operando2
	 * @return
	 * @throws IOException
	 */
	private static Process lanzarProcesoSumador(double operando1, double operando2) throws IOException {
		
		ProcessBuilder lanzadorProcesoSuma = new ProcessBuilder(Arrays.asList("java", SUMADOR, String.valueOf(operando1), String.valueOf(operando2)));
		lanzadorProcesoSuma.directory(new File(".\\target\\classes"));
		
		return lanzadorProcesoSuma.start();
		
	}
}
