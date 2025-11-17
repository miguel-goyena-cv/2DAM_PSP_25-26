package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

public class Pregunta {
	
	private String enunciado;
	private TipoPregunta tipo;
	
	public Pregunta(String enunciado, TipoPregunta tipo) {
		super();
		this.enunciado = enunciado;
		this.tipo = tipo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public TipoPregunta getTipo() {
		return tipo;
	}

	
	public String printInfo() {

		String resultado = "";
		
		resultado += "Pregunta: "+this.enunciado + "\n";
		resultado += "Tipo: "+this.tipo.toString() + "\n";
		
		return resultado;
		
	}
	
	

}
