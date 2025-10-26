package org.cuatrovientos.dam.psp.killenemies;

public enum AccionesJuego {
	
	MOSTRAR_PERSONAJES("Mostrar personajes de la partida", "1"),
	MOSTRAR_HEROE("Mostrar información Heroe", "2"),
	ATACAR_ENEMIGO("Atacar a un enemigo", "3"),
	DEFENDER_AMIGO("Defender a un amigo", "4"),
	GUARDAR_SALIR("Guardar y salir", "5"),
	BORRAR_SALIR("Borrar y salir", "6"),
	NO_ACCION("No hay Accion", "9999");
	
	
    private final String nombre;
    private final String indice; 

	AccionesJuego(String nombre, String indice) {
		this.nombre = nombre;
		this.indice = indice;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIndice() {
		return indice;
	}
	
	/**
	 * Devuleve un menu segun las acciones
	 * @return
	 */
	public static String montarMenuAcciones() {
		
		String resultadoMenu = "";
		for (AccionesJuego accion : AccionesJuego.values()) {
			if (accion != AccionesJuego.NO_ACCION)
				resultadoMenu = resultadoMenu+accion.indice+".- "+ accion.nombre+ "\n";
		}
		
		return resultadoMenu;

	}

	public static  AccionesJuego getAccionDeIndice(String indice) {
		for (AccionesJuego accion : AccionesJuego.values()) {
			if (accion.indice.equals(indice)) {
				return accion;
			}
		}
		return AccionesJuego.NO_ACCION;
		
	}

}
