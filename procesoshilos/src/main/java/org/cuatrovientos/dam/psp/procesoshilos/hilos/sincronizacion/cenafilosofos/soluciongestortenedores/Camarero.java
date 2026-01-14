package org.cuatrovientos.dam.psp.procesoshilos.hilos.sincronizacion.cenafilosofos.soluciongestortenedores;

import java.util.ArrayList;
import java.util.List;


/**
 * Este camarero es un camarero que da de 2 cubiertos a la vez.
 */
public class Camarero {
	
	private String nombre;
	private List<Tenedor> cuberteria = new ArrayList<>();
	
	
	public Camarero(String nombre, List<Tenedor> cuberteria) {
		super();
		this.nombre = nombre;
		this.cuberteria = cuberteria;
	}
	
	/**
	 * Se le pide un servicio al camarero, ese servicio se garantiza que esta libre
	 * @return Devuelve un servicio completo o null en el caso en que no haya ninguno completo
	 * @throws InterruptedException 
	 */
	public synchronized Servicio pedirServicio() throws InterruptedException {
		
		Tenedor tenedor1 = null;
		Tenedor tenedor2 = null;
		
		
		// Aqui eligo los tenedores que estan libres  y se los devolvera en parejas, o sea en un servicio
		for (Tenedor tenedor : cuberteria) {
			if (tenedor.estaLibre() && tenedor1 == null) {
				tenedor1 = tenedor;
			}
			else if (tenedor.estaLibre() && tenedor1 != null) {
				tenedor2 = tenedor;
			}
		}
		
		
		// Monto el servicio a partir de los tenedores y los bloqueo
		boolean servicioCompleto = (tenedor1 != null & tenedor2 != null);
		if (servicioCompleto) {
			// Aqui es donde bloqueo los tendores porque tengo un servicio completo
			tenedor1.cogerTenedor();
			tenedor2.cogerTenedor();
			return new Servicio(tenedor1, tenedor2);
		}
		else
			return null;
		
	}
	
	/**
	 * Se le devuelve un servicio al camarero y entonces esto lo limpia y lo deja disponible
	 * @param servicio
	 */
	public synchronized void devolverServicio(Servicio servicio) {
		
		// Libero los 2 tenedores
		servicio.liberar();
		
	}

	@Override
	public String toString() {
		return "Camarero [nombre=" + nombre + ", cuberteria=" + cuberteria + "]";
	}

}
