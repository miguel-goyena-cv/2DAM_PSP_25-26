package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Alumno implements Runnable{
	
	private static final int TIEMPO_BANO_SEGUNDOS = 30;
	
	private String nombre;
	private TipoAlumno tipo;
	private Examen examen;
	private Map<Pregunta, Boolean> resultadoPreguntas;
	private long timestampInicio;
	private boolean flagBano = false;
	private boolean flagFinalizar = false;
	private boolean debug = true;
	
	public Alumno(String nombre, TipoAlumno tipo, Examen examen) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.examen = examen;
		this.resultadoPreguntas = new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAlumno getTipo() {
		return tipo;
	}

	
	public Examen getExamen() {
		return examen;
	}

	@Override
	public void run() {
		ejecutaExamen();
	}
	
	/**
	 * Suspender al alumno es poner todas las preguntas como incorrectas
	 */
	public void suspender() {

		for (Pregunta preguntaASuspender: this.getExamen().getPreguntas()) {
			resultadoPreguntas.put(preguntaASuspender, false);
		}
		
	}
	
	/**
	 * Marco a un alumno para ir al baño.
	 * Luego el hilo atenderá la petición al final de cada Pregunta
	 */
	public void irAlBano() {

		flagBano = true; // Ya se encargará el hilo de hacer el sleep correspondiente
		
	}
	
	/**
	 * Devuelve un String con la información del alumno
	 * @return
	 */
	public String printInfo() {

		String resultado = "";
		
		resultado += this.nombre + "\n";
		resultado += "Tipo Alumno: \n";
		resultado += this.getTipo().printInfo();
		resultado += "Tiempo Examen (ms): " + (System.currentTimeMillis() - timestampInicio)+"\n";
		resultado += "Examen: \n";
		resultado += this.examen.printInfo();
		resultado += "Resultado: \n";
		for (Map.Entry<Pregunta, Boolean> entry : this.resultadoPreguntas.entrySet()) {
			resultado += "\tPregunta: "+entry.getKey().getEnunciado()+", Correcta: "+entry.getValue()+"\n";
		}
		
		return resultado;
		
	}
	
	/**
	 * Marco a un alumno para ir finalizando
	 * Luego el hilo atenderá la petición al final de cada Pregunta
	 */
	public void irFinalizando() {
		
		flagFinalizar = true;
		
	}

	/**
	 * Ejecuta el examen
	 */
	private void ejecutaExamen() {
		
		this.timestampInicio = System.currentTimeMillis();
		logConTimestamp("Inicia el examen");
		
		// Iteramos la lista de preguntas
		for (Pregunta pregunta: examen.getPreguntas()) {
			
			contestoPregunta(pregunta);
			
			// Miro a ver si me han marcado que puedo ir al baño
			if (flagBano) {
				logConTimestamp("Voy al Baño!!!");
				try {
					Thread.sleep(TIEMPO_BANO_SEGUNDOS);
				} catch (InterruptedException e) {
					System.err.println("Error en la simulación del examen, se acaba el examen");
					break;
				}
				flagBano = false;
			}
			
			// Mira a ver si me han dicho que se ha acabado el tiempo
			if (flagFinalizar) {
				logConTimestamp("Voy acabando, no puedo responder más preguntas!!");
				break;
			}
		}
		
		// COntesto la pregunta extra
		if (!flagFinalizar && this.getExamen().getPreguntaExtra() != null) {
			contestoPregunta(this.getExamen().getPreguntaExtra());
		}
		
		// Dejamos un tiempo para que vacie el buffer de escritura
		try {
			Thread.sleep(Duration.ofSeconds(10));
		} catch (InterruptedException e) {
			System.err.println("Error en la simulación del examen, se acaba el examen");
		}
		logConTimestamp("Finaliza el examen");
		
	}

	private void contestoPregunta(Pregunta pregunta) {
		
		logConTimestamp("Lee la pregunta: "+pregunta.getEnunciado());
		
		// Ejecutamos el tiempo de pregunta
		Duration tiempoRespuestaMedio = pregunta.getTipo().getTiempoRespuesta();
		Duration tiempoRespuesta = calcularTiempoRespuestaAlumno(tiempoRespuestaMedio);
		
		// Simulamos el tiempo de respuesta
		try {
			Thread.sleep(tiempoRespuesta);
		} catch (InterruptedException e) {
			System.err.println("Error en la simulación del examen, se acaba el examen");
		}
		
		// Calculamos el OK o ERROR de la pregunta
		boolean respuestaCorrecta = calcularExitoRespuesta();
		logConTimestamp("Contesta a la pregunta: "+pregunta.getEnunciado() + ", EXITO¿?: "+respuestaCorrecta);
		this.resultadoPreguntas.put(pregunta, respuestaCorrecta);
		
	}

	/**
	 * Calcula el tiempo de respuesta de una pregunta para ese alumno.
	 * tiempoRespuestaMedio * porcentaje de ese alumno
	 * @param tiempoRespuestaMedio
	 * @return
	 */
	private Duration calcularTiempoRespuestaAlumno(Duration tiempoRespuestaMedio) {
		
		long tiempoSegundos = tiempoRespuestaMedio.getSeconds();
		int porcentajeTiempoRandom = (int) ((int)(Math.random() * (this.tipo.getPorcentajeTiempoMaximo()-this.tipo.getPorcentajeTiempoMinimo())) + this.tipo.getPorcentajeTiempoMinimo()); // 30 a 40
		long tiempoSegundosParaEseAlumno = (long) (tiempoSegundos + ((tiempoSegundos*porcentajeTiempoRandom)/100));
		return Duration.ofSeconds(tiempoSegundosParaEseAlumno);
		
	}
	
	/**
	 * De forma aleatoria calcula el exito o fracaso de una respuesta
	 * @return
	 */
	private boolean calcularExitoRespuesta() {
	    double probabilidad = Math.random(); // genera un número entre 0 y 1
	    return probabilidad < this.tipo.getProbabilidadAcierto(); // éxito si está por debajo del porcentaje
	}
	
	/**
     * Escribe el log junto con los ms desde comienzo hilo
     * @param log
     */
    private void logConTimestamp(String log){
    	if (debug)
    		System.out.println("["+Thread.currentThread().threadId()+"]["+this.nombre+"]; Timestamp: "+(System.currentTimeMillis() - timestampInicio)+": "+log);
    }
	
	
}
