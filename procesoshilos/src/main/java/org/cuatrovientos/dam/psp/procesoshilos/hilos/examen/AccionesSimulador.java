package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

public enum AccionesSimulador {
	
	INFO_EXAMEN("Recoger la información del examen en curso", "1"),
	PAUSA_ALUMNO_BANO("Dejar a un alumno ir al baño", "2"),
	EXPULSION_ALUMNO_COPIA("Expulsar a un alumno que copia", "3"),
	ANADIR_1_PREGUNTA("Añadir una preguntar al examen", "4"),
	FINALIZAR_EXAMEN("Se acabo el tiempo, finalizar examen", "5"),
	NO_ACCION("No hay Accion", null);
	
	
    private final String descripcion;
    private final String indice; 

	AccionesSimulador(String descripcion, String indice) {
		this.descripcion = descripcion;
		this.indice = indice;
	}

	public String getDescripcion() {
		return descripcion;
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
		for (AccionesSimulador accion : AccionesSimulador.values()) {
			if (accion != AccionesSimulador.NO_ACCION)
				resultadoMenu = resultadoMenu+accion.indice+".- "+ accion.descripcion+ "\n";
		}
		
		return resultadoMenu;

	}

	public static  AccionesSimulador getAccionDeIndice(String indice) {
		for (AccionesSimulador accion : AccionesSimulador.values()) {
			if (accion.indice.equals(indice)) {
				return accion;
			}
		}
		return AccionesSimulador.NO_ACCION;
		
	}

}
