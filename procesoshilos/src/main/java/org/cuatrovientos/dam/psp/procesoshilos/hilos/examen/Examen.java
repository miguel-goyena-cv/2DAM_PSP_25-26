package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

import java.util.ArrayList;
import java.util.List;

public class Examen {
	
	private String nombreAsignatura;
	private List<Pregunta> preguntas;
	private Pregunta preguntaExtra;
	
	
	public Examen(String nombreAsignatura) {
		super();
		this.nombreAsignatura = nombreAsignatura;
		this.preguntas = new ArrayList<>();
	}


	public String getNombreAsignatura() {
		return nombreAsignatura;
	}
	
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}


	public void anadirPreguntar(Pregunta pregunta) {
		this.preguntas.add(pregunta);
	}
	
	public void setPreguntaExtra(Pregunta pregunta) {
		this.preguntaExtra = pregunta;
	}
	
	public Pregunta getPreguntaExtra() {
		return this.preguntaExtra;
	}


	public String printInfo() {

		String resultado = "";
		
		resultado += this.nombreAsignatura + "\n";
		resultado += "Examen: \n";
		for (Pregunta pregunta: preguntas) {
			resultado += pregunta.printInfo();
		}
		if (this.getPreguntaExtra() != null) {
			resultado += "Pregunta Extra: " + this.getPreguntaExtra().printInfo() + "\n";
		}

		return resultado;
		
	}
	

}
