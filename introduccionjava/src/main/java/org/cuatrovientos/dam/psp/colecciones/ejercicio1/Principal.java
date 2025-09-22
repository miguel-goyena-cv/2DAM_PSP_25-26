package org.cuatrovientos.dam.psp.colecciones.ejercicio1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Principal {
	
	static List<Receta> recetas;

	public static void main(String[] args) {

		recetas = new ArrayList<>();
		
		// Rellenamos la BBDD
		rellenarRecretas();
		
		// Mostrar recetas
		System.out.println("Lista de recetas: ");
		System.out.println(recetas.toString());
		System.out.println("======================================");
		
		// Buscar por postre
		List<Receta> recetasPostre = buscarPorTipo(TipoReceta.Postre);
		System.out.println("Lista de recetas de tipo: "+ TipoReceta.Postre);
		System.out.println(recetasPostre.toString());
		System.out.println("======================================");
		
		// Eliminamos por nombre
		eliminarRecetaPorNombre("Pizza");
		System.out.println("Lista de recetas despues de eliminar la PIZZA: ");
		System.out.println(recetas.toString());
		System.out.println("======================================");
		
	}

	private static void eliminarRecetaPorNombre(String nombreEliminar) {
		
		// Vamos a utilizar predicados, YUJUU!!!
		recetas.removeIf(r -> r.getNombre().equals(nombreEliminar));
	}

	private static void rellenarRecretas() {
		
		Receta recetaPizza = new Receta("Pizza", Duration.ofMinutes(60), TipoReceta.Principal);
		Receta recetaEnsalada = new Receta("Ensalada verde", Duration.ofMinutes(10), TipoReceta.Entrante);
		Receta recetaPanTumaca = new Receta("Pan Tumaca", Duration.ofSeconds(45), TipoReceta.Entrante);
		Receta recetaALeche = new Receta("Arroz con leche", Duration.ofMinutes(30), TipoReceta.Postre);
		Receta recetaTiramisu = new Receta("Hamburguesa del Fosters", Duration.ZERO, TipoReceta.Principal);
		
		recetas.add(recetaTiramisu);
		recetas.add(recetaALeche);
		recetas.add(recetaPanTumaca);
		recetas.add(recetaEnsalada);
		recetas.add(recetaPizza);
		
	}
	
	private static List<Receta> buscarPorTipo(TipoReceta tipo) {

		List<Receta> resultado = new ArrayList<>();
		
		// Realizamos la busqueda.
		for (Receta receta : recetas) {
			if (receta.getTipo().equals(tipo)) {
				resultado.add(receta);
			}
		}
		
		return resultado;
	}
}
