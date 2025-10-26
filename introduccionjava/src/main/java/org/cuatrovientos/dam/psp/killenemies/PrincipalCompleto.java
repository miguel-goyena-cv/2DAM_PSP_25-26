package org.cuatrovientos.dam.psp.killenemies;

import java.util.Scanner;

public class PrincipalCompleto {

	public static void main(String[] args) {

		
		try (Scanner scanner = new Scanner(System.in);){ // Meto aqui el scanner, eso me garantiza que lo va a cerrar en el finally
			
			// Pregunto por el numbre del Heroe
			String nombreHeroe = pedirNombreHeroe(scanner);
			
			// Carga la partida si existiera, sino crea una nueva
			Partida partida = new Partida(nombreHeroe);
			if (partida.existePartidaGuardada()) {
				partida.cargarPartidaGuardada();
			}
			else {
				partida.crearPersonajes(10); // Creo 10 personajes por defecto
			}
				
			
			// Juego ahora con mi heroe
			jugarHeroe(partida, scanner);
			
		}
		catch (Exception e) {
			System.err.println("Error incontrolado en el programa principal: " + e.getMessage());
			e.printStackTrace();
		}

	}


	/**
	 * Devuelve el nombre del heroe por pantalla.
	 * @param scanner
	 * @return Lo que introduce el usuario y si no devuelve Default.
	 */
	private static String pedirNombreHeroe(Scanner scanner) {
		
		System.out.print("Dime como se va a llamar tu heroe: ");
		String nombreHeroe = scanner.nextLine();
		if (nombreHeroe.isBlank() || nombreHeroe.isEmpty()) {
			System.out.print("Como no me lo dices le llamaremos Default!!");
			nombreHeroe = "Default";
		}
		return nombreHeroe;
		
	}
	
	private static void jugarHeroe(Partida partida, Scanner scanner) {

		boolean continuar = true;
		while (continuar) {
			
			System.out.println(AccionesJuego.montarMenuAcciones());
			System.out.print("Que quiere realizar con su heroe: ");
			String indice = scanner.nextLine();
			AccionesJuego accionElegida = AccionesJuego.getAccionDeIndice(indice);
			
			switch (accionElegida) {
				case MOSTRAR_PERSONAJES:
					System.out.println("Muestro los personajes");
					partida.mostrarPersonajes();
					break;
				case MOSTRAR_HEROE:
					System.out.println("Muestro estadísticas heroe");
					partida.mostrarInfoHeroe();
					break;
				case ATACAR_ENEMIGO:
					System.out.println("Ataquemos enemigos!!");
					partida.atacarEnemigo(scanner);
					break;
				case DEFENDER_AMIGO:
					System.out.println("Defendamos amigos!!");
					partida.defenderAmigo(scanner);
					break;
				case GUARDAR_SALIR:
					System.out.println("Guardamos tu juego: BYE BYE");
					continuar = false;
					partida.guardarPartida();
					break;
				case BORRAR_SALIR:
					System.out.println("Borramos tu juego: BYE BYE");
					continuar = false;
					partida.eliminarPartida();
					break;
				default:
					System.out.println("Has elegido mal tus opciones");
					
			}
			
			System.out.println("===========================================");
				
			
		}
		
		
	}

}
