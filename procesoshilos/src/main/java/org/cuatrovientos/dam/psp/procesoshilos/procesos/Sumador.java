package org.cuatrovientos.dam.psp.procesoshilos.procesos;

public class Sumador {

	public static void main(String[] args) {
		
		// Recupero los sumando
		float sumando1 = Float.valueOf(args[0]);
		float sumando2 = Float.valueOf(args[1]);
		
		float resultado = sumando1 + sumando2;
		System.out.println(resultado);
		System.exit(200);

	}

}
