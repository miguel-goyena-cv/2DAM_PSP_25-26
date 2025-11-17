package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

import java.time.Duration;


public enum TipoPregunta {
	
	VERDADERO_FALSO("Verdadero o Falso", Duration.ofSeconds(30), "1"),
	RESPUESTA_CORTA("Respuesta Corta", Duration.ofSeconds(60), "2"),
	NO_TYPE("Sin Tipo", null, null);
	
	
    private final String descripcion;
    private final Duration tiempoRespuesta; 
    private final String indice;

    TipoPregunta(String descripcion, Duration tiempoRespuesta, String indice) {
		this.descripcion = descripcion;
		this.tiempoRespuesta = tiempoRespuesta;
		this.indice = indice;
	}

	public String getDescripcion() {
		return descripcion;
	}


	public Duration getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public String getIndice() {
		return indice;
	}

	/**
	 * Devuelve el menu de TiposPreguntas
	 * @return
	 */
	public static String montarMenuTipos() {
		
		String resultadoMenu = "";
		for (TipoPregunta tipo : TipoPregunta.values()) {
			if (tipo != TipoPregunta.NO_TYPE)
				resultadoMenu = resultadoMenu+tipo.indice+".- "+ tipo.descripcion+ "\n";
		}
		
		return resultadoMenu;

	}

	public static  TipoPregunta getTipoDeIndice(String indice) {
		for (TipoPregunta tipo : TipoPregunta.values()) {
			if (tipo.indice.equals(indice)) {
				return tipo;
			}
		}
		return TipoPregunta.NO_TYPE;
		
	}

}
