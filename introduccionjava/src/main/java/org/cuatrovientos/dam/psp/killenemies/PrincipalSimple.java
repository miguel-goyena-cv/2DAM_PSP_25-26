package org.cuatrovientos.dam.psp.killenemies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrincipalSimple {

	public static void main(String[] args) {
		
		try {
			
			// Creo 10 personajes
			List<IPersonaje> listaPersonajes = crearPersonajes(10);
			
			// Los desordeno
			Collections.shuffle(listaPersonajes);
			
			// Pinta los personajes y mata a los enemigos
			muestraPersonajeMatandoEnemigos(listaPersonajes); // TODO no me acaba de gustar mezclar responsabilidades

		} catch (Exception e) {
			System.err.println("Error incontrolado en el programa principal: " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Itera la lista y mata enemigos.
	 * @param listaPersonajes
	 */
	private static void muestraPersonajeMatandoEnemigos(List<IPersonaje> listaPersonajes) {

		for (int i = 0; i < listaPersonajes.size(); i++) {
			
			// No me acaba de gustar este enfoque de isEnemy y luego el CAST.
			boolean isEnemy = listaPersonajes.get(i).isEnemy() &&  (listaPersonajes.get(i) instanceof Enemigo); 
			if (isEnemy) {
				System.out.println("El personaje: "+ i + " es Enemigo");
				((Enemigo)listaPersonajes.get(i)).kill();
			}
			else {
				System.out.println("El personaje: "+ i + " es Amigo");
			}
			
		}
		
	}

	/**
	 * Crea una lista de personajes siguiendo la serie Enemy - Friend - Enemy - Friend -...
	 * @param numeroPersonajes El número de personajes a crear
	 */
	private static List<IPersonaje> crearPersonajes(int numeroPersonajes) {
		
		List<IPersonaje> listaPersonajes = new ArrayList<>();
		for (int i = 0; i<numeroPersonajes; i++) {
			if (i % 2 == 0) {
				listaPersonajes.add(new Enemigo());
			}
			else {
				listaPersonajes.add(new Amigo());
			}
		}
		
		return listaPersonajes;
	}

}
