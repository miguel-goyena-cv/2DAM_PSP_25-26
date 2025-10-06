package org.cuatrovientos.dam.psp.excepciones;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		Scanner scanner = null;
		try {
			System.out.println("Bienvenido a la calculadora de numeros naturales");
			
	        // Creamos un objeto Scanner para leer desde consola
	        scanner = new Scanner(System.in);
			
	        // Preparamos el bucle para repetir la operación
	        boolean continuar = true;
	        while (continuar) {

	        	// Pedimos los numeros por pantalla
	            System.out.print("Introduce el primer número: ");
	            int num1 = Integer.parseInt(scanner.nextLine());
	            System.out.print("Introduce el segundo número: ");
	            int num2 = Integer.parseInt(scanner.nextLine());
	            
	            CalculadoraNaturales cn = new CalculadoraNaturales(num1, num2);
	            
	            // Hago la operacion
	            try {
	            	int resultado = cn.resta();
	            	System.out.println("Resultado: "+resultado);
	            }
	            catch (NegativeSubstractException e1) {
	            	System.out.println("Resultado negativo!!: "+e1.getMessage());
	            }
	            catch (IllegalArgumentException e2) {
	            	System.out.println(e2.getMessage());
				}

	            // Pregunto si quiero otra operacion
	            System.out.print("Quiere realizar otra operación (S/n): ");
	            String respuesta = scanner.nextLine();
	            continuar = (respuesta.equalsIgnoreCase("s"));
	            
	        }
		}
		catch (Exception e) {
			System.out.println("Error general en el programa: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			// Siempre cierro el scanner!!!!
			if (scanner != null)
				scanner.close();
		}

	}

}
