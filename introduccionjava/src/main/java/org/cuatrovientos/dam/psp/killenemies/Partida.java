package org.cuatrovientos.dam.psp.killenemies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Partida{

	private Heroe heroe;
	private List<IPersonaje> personajes;
	
	
	public Partida(String nombreHeroe) {
	
		heroe = new Heroe(nombreHeroe);
		personajes = new ArrayList<>();
		
	}

	/** 
	 * Crea una partida aleatoria con sus personajes
	 */
	public void crearPersonajes(int numeroPersonajes) {
		
		personajes = new ArrayList<>();
		for (int i = 0; i<numeroPersonajes; i++) {
			if (i % 2 == 0) {
				personajes.add(new Enemigo());
			}
			else {
				personajes.add(new Amigo());
			}
		}
		
		Collections.shuffle(personajes);
		
	}
	
	/// Todo lo referente a las acciones de la partida
	
	/**
	 * Muestra los personajes por pantalla
	 */
	public void mostrarPersonajes() {
		
		for (int i = 0; i < personajes.size(); i++) {
			
			boolean isEnemy = personajes.get(i).isEnemy(); 
			if (isEnemy) {
				System.out.println("["+ i + "] es Enemigo");
			}
			else {
				System.out.println("["+ i + "] es Amigo");
			}
			
		}
	}
	
	/**
	 * Muestra la información del heroe
	 */
	public void mostrarInfoHeroe() {
		
		System.out.println(this.heroe.toString());
		
	}
	
	
	/**
	 * Atacar a un personaje que pido por consola. Puede ser amigo o enemigo 
	 * Si es enemigo lo mato y doy puntos al heroe
	 * Si es un amigo lo mato simplemente
	 * @param scanner
	 */
	public void atacarEnemigo(Scanner scanner) {
		
		// Muestro la lista y pido a quien se quiere atacar
		mostrarPersonajes();
		System.out.print("Dime a quien quieres atacar de esta lista: ");
		int indice = 0;
		try {
			indice = obtenerPersonajeLista(scanner);
		} catch (Exception e) {
			System.out.print("Dime un indice correcto");
			return;
		}
		
		// Si es un enemigo lo mato, lo quito de la lista y sumo puntos al heroe
		// Si es un amigo tambien lo quito de la lista pero no sumo puntos al heroe
		if (this.personajes.get(indice) instanceof Enemigo) {
			((Enemigo)this.personajes.get(indice)).kill();
			this.personajes.remove(indice);
			this.heroe.incrementaContadorEnemigos();
		}
		else if (this.personajes.get(indice) instanceof Amigo){
			System.out.println("Te has cargado a un amigo!!");
			this.personajes.remove(indice);
		}
		
	}
	
	/**
	 * Defender a un personaje. Puede ser amigo o enemigo 
	 * Si es amigo doy puntos al heroe
	 * Si es un enemigo lo duplico
	 * @param scanner
	 */
	public void defenderAmigo(Scanner scanner) {
		
		// Muestro la lista y pido a quien se quiere atacar
		mostrarPersonajes();
		System.out.print("Dime a quien quieres defender de esta lista: ");
		int indice = 0;
		try {
			indice = obtenerPersonajeLista(scanner);
		} catch (Exception e) {
			System.out.print("Dime un indice correcto");
			return;
		}
		
		// Si es un amigo sumo puntos al heroe
		// Si es un enemigo lo duplico en la lista
		if (this.personajes.get(indice) instanceof Amigo) {
			((Amigo)this.personajes.get(indice)).heal();
			this.heroe.incrementaContadorAmigos();
		}
		else if (this.personajes.get(indice) instanceof Enemigo){
			System.out.println("Has defendido a un enemigo, lo duplicaste!!!");
			this.personajes.add(new Enemigo());
		}
		
	}
	
	/**
	 * Devuelve un indice pedido por pantalla
	 * @param scanner
	 * @return El indice de la lista
	 */
	private int obtenerPersonajeLista(Scanner scanner) throws Exception{
		
		String indiceString = scanner.nextLine();
		int indice = Integer.parseInt(indiceString);
		if (indice < 0 || indice > this.personajes.size()-1) {
			throw new Exception("Indice incorrecto");
		}
		return indice;
	}
	
	/// Todo lo referente a la carga de partidas

	/**
	 * Devuelve si existe un fichero de partida guardada
	 * @return
	 */
	public boolean existePartidaGuardada() {

		File ficheroGuardado = new File(getNombreFichero());
		return (ficheroGuardado.exists());

	}

	/**
	 * Carga la partida de un fichero
	 */
	public void cargarPartidaGuardada() {

		// Si no hay fichero no devuelve nada
		if (!existePartidaGuardada()) {
			System.err.println("OJO estas cargando una partida cuando no hay fichero");
			return;
		}
		
		// Recuperamos el fichero
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(getNombreFichero())))) {
        	heroe = (Heroe) ois.readObject();
        	personajes = (List<IPersonaje>) ois.readObject();
            System.out.println("Partida cargada correctamente");
        } catch (Exception e) {
			System.err.println("Error en la carga del fichero: "+e.getMessage());
			return;
        }
		
	}
	
	/**
	 * Guarda una partida en un fichero
	 */
	public void guardarPartida() {
		
        File game = new File(getNombreFichero());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(game))) {
            oos.writeObject(heroe);
            oos.writeObject(personajes);
            System.out.printf("Partida guardada en %s%n", game.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar partida, no guardo nada: "+e.getMessage());
        }
		
	}
	
	/**
	 * Elimina la partida
	 */
	public void eliminarPartida() {
		
        File game = new File(getNombreFichero());
        
		// Si no hay fichero no hago nada
		if (!existePartidaGuardada()) {
			System.err.println("OJO estas borrando una partida cuando no hay fichero");
			return;
		}
		
		// Borro
		game.delete();
		
	}
	

	
	/**
	 * Devuelve el nombre del fichero de guardado
	 * @return
	 */
	private String getNombreFichero() {
		return heroe.getNombre()+".dat";
	}
	

}
