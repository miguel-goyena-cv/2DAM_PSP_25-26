package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

public enum TipoAlumno {
	
	MUY_BUENO("Alumno rápido y correcto", -70,-50, 0.95f, "1"),
	PRECIPITADO("Alumno rápido pero que no responde correctamente (Precipitado)", -80, -70, 0.3f, "2"),
	SEGURO("Alumno lento pero responde correctamente", 20, 30, 0.9f, "3"),
	MUY_MALO("Alumno lento que no responde correctamente", 30, 40, 0.2f, "4"),
	NO_TYPE("Sin Tipo", 0, 0, 0, null);
	
	
    private final String descripcion;
    private final float porcentajeTiempoMinimo; // Cuanto más rápido (>0) o lento (<0) es el alumno con respecto a la media
    private final float porcentajeTiempoMaximo; // Cuanto más rápido (>0) o lento (<0) es el alumno con respecto a la media
    private final float probabilidadAcierto; // La probabilidad de acierto 0 -1
    private final String indice;

    TipoAlumno(String descripcion, float porcentajeTiempoMinimo, float porcentajeTiempoMaximo, float probabilidadAcierto, String indice) {
		this.descripcion = descripcion;
		this.porcentajeTiempoMinimo = porcentajeTiempoMinimo;
		this.porcentajeTiempoMaximo = porcentajeTiempoMaximo;
		this.probabilidadAcierto = probabilidadAcierto;
		this.indice = indice;
	}
    

	public String getDescripcion() {
		return descripcion;
	}


	public float getPorcentajeTiempoMinimo() {
		return porcentajeTiempoMinimo;
	}

	public float getPorcentajeTiempoMaximo() {
		return porcentajeTiempoMinimo;
	}

	public float getProbabilidadAcierto() {
		return porcentajeTiempoMaximo;
	}


	public String getIndice() {
		return indice;
	}


	/**
	 * Devuelve el menu de TiposPreguntas
	 * @return
	 */
	public static String montarMenuTiposPreguntas() {
		
		String resultadoMenu = "";
		for (TipoAlumno tipo : TipoAlumno.values()) {
			if (tipo != TipoAlumno.NO_TYPE)
				resultadoMenu = resultadoMenu+tipo.indice+".- "+ tipo.descripcion+ "\n";
		}
		
		return resultadoMenu;

	}

	public static  TipoAlumno getTipoDeIndice(String indice) {
		for (TipoAlumno tipo : TipoAlumno.values()) {
			if (tipo.indice.equals(indice)) {
				return tipo;
			}
		}
		return TipoAlumno.NO_TYPE;
		
	}


	String printInfo() {
		
		String resultado = "";
		
		resultado += "Nombre: "+this.name() + "\n";
		resultado += "Descripcion: "+this.descripcion + "\n";
		resultado += "Porcentaje Tiempo: ["+this.porcentajeTiempoMinimo +"-"+ this.porcentajeTiempoMaximo + "]%\n";
		resultado += "Probabilidad Acierto: "+this.probabilidadAcierto + "\n";
		
		return resultado;
		
	}

}
