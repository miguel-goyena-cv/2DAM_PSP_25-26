package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos.soluciongestortenedores;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Esta solución de la cena de los filósofos hace que los cubiertos se gestionen por un camarero 
 * Este camarero se dedica a dar un servicio(2 tenedores) libres al filosofo para que coma con tranquilidad.
 * El filósofo espera a que el camarero los cubiertos. Y mientras ni come ni piensa, simplemente espera.
 * 
 * Tiene pegas:
 * 
 * 1.- Hay un cubierto que siempre esta inutilizado
 * 2.- Se forma una cola de filosofos que ni piensan, ni comen y a los filosofos les pagan por pensar!!!
 */

public class CenaFilosofos {

	public static void main(String[] args) {

		// Lo genero aqui para poder pararlo en el caso de fallo catastrofico
		Thread hiloAristoteles = null;
		Thread hiloKant = null;
		Thread hiloSartre = null;
		Thread hiloPlaton = null;
		Thread hiloPopper = null;
		
		try {
			
			//Vamos a crear los 5 tenedores y se los damos al camarero
			List<Tenedor> listaTenedores = crearTenedores();
			Camarero camareroMiguel = new Camarero("GOYENA", listaTenedores); // RECURSO COMPARTIDO
			
			// Vamos a crear los filosofos, esta vez no les damos tenedores, sino acceso al camarero
			Filosofo aristoteles = new Filosofo("Aristoteles", camareroMiguel);
			Filosofo kant = new Filosofo("Kant", camareroMiguel);
			Filosofo sartre = new Filosofo("Sartre", camareroMiguel);
			Filosofo platon = new Filosofo("Platon", camareroMiguel);
			Filosofo popper = new Filosofo("Popper", camareroMiguel);
			
			//Ahoro lanzo a los filosofos
			hiloAristoteles = despiertaFilosofo(aristoteles);
			hiloKant = despiertaFilosofo(kant);
			hiloSartre = despiertaFilosofo(sartre);
			hiloPlaton = despiertaFilosofo(platon);
			hiloPopper = despiertaFilosofo(popper);
			
			// Ahora me quedo esperando hasta que mueran en lapsos de 10 segundos
			boolean estanTodosMuertos = estanTodosMuertos(aristoteles, kant, sartre, platon, popper);
			while(!estanTodosMuertos) {
				
				Thread.sleep(Duration.ofSeconds(10));
				logMain(aristoteles.toString());
				logMain(kant.toString());
				logMain(sartre.toString());
				logMain(platon.toString());
				logMain(popper.toString());
				logMain(camareroMiguel.toString());
				
			}
			
			
		}
		catch(Exception excepcionMain) {
			System.err.println("Excepcion descontrolada en el hilo general: "+excepcionMain.getMessage()+", Cierro el programa principal");
			excepcionMain.printStackTrace();
			cerrarHilosPendientes(hiloAristoteles, hiloKant, hiloSartre, hiloPlaton, hiloPopper);
		}
			
	}

	private static List<Tenedor> crearTenedores() {
		Tenedor tenedor1 = new Tenedor(1);
		Tenedor tenedor2 = new Tenedor(2);
		Tenedor tenedor3 = new Tenedor(3);
		Tenedor tenedor4 = new Tenedor(4);
		Tenedor tenedor5 = new Tenedor(5);
		return Arrays.asList(tenedor1, tenedor2, tenedor3, tenedor4, tenedor5);
	}
	
	private static Thread despiertaFilosofo(Filosofo filosofo) {
		
		Thread hiloFilosofo = new Thread(filosofo);
		hiloFilosofo.start();
		return hiloFilosofo;
		
	}
	
	private static boolean estanTodosMuertos(Filosofo... filosofos) {
		
		// En cuanto un filosofo esta vivo ya no pregunto mas
		for (Filosofo filosofo: filosofos) {
			if (filosofo.estaVivo())
				return false;
		}
		
		return true;
		
	}
	
	/**
	 * Interrumpe los hilos que queramos
	 * @param hilos
	 */
	private static void cerrarHilosPendientes(Thread... hilos) {
		
		for (Thread hilo: hilos) {
			if (hilo != null && hilo.isAlive()) {
				hilo.interrupt();
			}
		}
		
	}
	

    private static void logMain(String log){
    	System.out.println("["+Thread.currentThread().threadId()+"][MAIN]: "+log);
    }

}
