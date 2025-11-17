package org.cuatrovientos.dam.psp.procesoshilos.hilos.examen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class SimuladorExamen {


	public static void main(String[] args) {
		

		try (Scanner scanner = new Scanner(System.in);) { // Meto aqui el scanner, eso me garantiza que lo va a cerrar en el finally

			// Pregunto por los datos del examen
			Examen examen = pedirDatosExamen(scanner);
			
			// Pregunto por los datos de los alumnos y les inyecto el examen
			List<Alumno> alumnos = pedirDatosAlumnos(scanner, examen);
			
			// Lanzo los hilos de los alumnos
			Map<Alumno, Thread> hilosAlumno = lanzarHilosAlumno(alumnos);
			
			// Muestro menu de acciones
			lanzarAccionesProfesor(scanner, examen, hilosAlumno);

		} catch (Exception e) {
			System.err.println("Error incontrolado en el programa principal: " + e.getMessage());
			e.printStackTrace();
		}

	}


	/**
	 * Pide por pantalla los datos para el Examen a simular, junto con sus preguntas
	 *  y monta la estructura de examen
	 * @param scanner
	 * @return
	 */
	private static Examen pedirDatosExamen(Scanner scanner) {
		
		// Pedimos asignatura
		System.out.print("Dime la asignatura de tu examen: ");
		String asignatura = scanner.nextLine();
		if (asignatura.isBlank() || asignatura.isEmpty()) {
			System.out.print("Como no me lo dices le llamaremos PSP");
			asignatura = "PSP";
		}
		Examen examenNuevo = new Examen(asignatura);
		
		// Pedimos preguntas
		boolean masPreguntas = true;
		while (masPreguntas) {
			System.out.println("Dime un enunciado para la pregunta, si lo dejas en blanco es que ya no quieres más preguntas: ");
			String enunciado = scanner.nextLine();
			if (enunciado.isBlank() || enunciado.isEmpty()) {
				System.out.println("No quieres más preguntas");
				masPreguntas = false;
			}
			else {
				// Pedimos el tipo de pregunta
				String menuTipos = TipoPregunta.montarMenuTipos();
				System.out.println(menuTipos);
				System.out.print("Dime el indice: ");
				String tipoPreguntaIndice = scanner.nextLine();
				TipoPregunta tipoPregunta = TipoPregunta.NO_TYPE;
				if (tipoPreguntaIndice.isBlank() || tipoPreguntaIndice.isEmpty()) {
					System.out.println("Como no me lo dices le pondremos Verdadero o Falso");
					tipoPregunta = TipoPregunta.VERDADERO_FALSO;
				}
				else {
					tipoPregunta = TipoPregunta.getTipoDeIndice(tipoPreguntaIndice);
				}
				Pregunta preguntaNueva = new Pregunta(enunciado, tipoPregunta);
				examenNuevo.anadirPreguntar(preguntaNueva);
			}
		}
		
		return examenNuevo;
	}
	
	/**
	 * Pide los datos del alumno y le inyectamos al alumno los datos del examen
	 * @param scanner
	 * @param examen
	 * @return
	 */
	private static List<Alumno> pedirDatosAlumnos(Scanner scanner, Examen examen) {

		List<Alumno> listaAlumnos = new ArrayList<>();
		
		// Pedimos los alumnos
		boolean masAlumnos = true;
		while (masAlumnos) {
			System.out.println("Dime el nombre del alumno, si lo dejas en blanco es que ya no quieres más alumnos: ");
			String nombreAlumno = scanner.nextLine();
			if (nombreAlumno.isBlank() || nombreAlumno.isEmpty()) {
				System.out.println("No quieres más alumnos");
				masAlumnos = false;
			}
			else {
				// Pedimos el tipo alumno
				String menuTiposAlumno = TipoAlumno.montarMenuTiposPreguntas();
				System.out.println(menuTiposAlumno);
				System.out.print("Dime el indice: ");
				String tipoAlumnoIndice = scanner.nextLine();
				TipoAlumno tipoAlumno = TipoAlumno.NO_TYPE;
				if (tipoAlumnoIndice.isBlank() || tipoAlumnoIndice.isEmpty()) {
					System.out.println("Como no me lo dices le pondremos Alumno muy bueno");
					tipoAlumno = TipoAlumno.MUY_BUENO;
				}
				else {
					tipoAlumno = TipoAlumno.getTipoDeIndice(tipoAlumnoIndice);
				}
				Alumno alumnoNuevo = new Alumno(nombreAlumno, tipoAlumno, examen);
				listaAlumnos.add(alumnoNuevo);
			}
		}
		
		return listaAlumnos;
		
	}
	
	private static Map<Alumno, Thread> lanzarHilosAlumno(List<Alumno> alumnos) {

		Map<Alumno, Thread> infoAlumnos = new HashMap<>();
		
		// Por cada alumno generamos un Thread y lo lanzamos
		for (Alumno alumno: alumnos) {
			Thread hilo = new Thread(alumno);
			hilo.start(); // Lanzo el HILO!!!
			infoAlumnos.put(alumno, hilo);
		}
		
		return infoAlumnos;
	}
	

	private static void lanzarAccionesProfesor(Scanner scanner, Examen examen, Map<Alumno, Thread> hilosAlumno) {

		
		boolean continuar = true;
		Profesor yoMismo = new Profesor();
		
		while (continuar) {
			
			// Aqui vamos a lanzar un menu de acciones y esperamos accion
			System.out.println("=======================================");
			System.out.println("Acciones del profesor: ");
			System.out.println(AccionesSimulador.montarMenuAcciones());
			System.out.print("Elija una accion: ");
			String indiceAccion = scanner.nextLine();
			AccionesSimulador accionElegida = AccionesSimulador.getAccionDeIndice(indiceAccion);
			
			
			// Dependiendo de lo elegido lanzo la accion correspondiente
			switch (accionElegida) {
			
				case ANADIR_1_PREGUNTA:
					
					// Pido la pregunta nueva y realizo la accion del profesor
					Pregunta preguntaNueva = pedirPreguntaNueva(scanner);
					yoMismo.anadirPregunta(hilosAlumno, preguntaNueva);
					break;
					
				case EXPULSION_ALUMNO_COPIA:
					
					// Pido el alumno a expulsar y realizo la accion del profesor
					Map.Entry<Alumno, Thread> alumnoAExpulsar = pedirAlumno(scanner, hilosAlumno);
					
					if (alumnoAExpulsar == null) {
						System.err.println("Alumno no encontrado!!!");
					}
					else {
						yoMismo.expulsarAlumnno(alumnoAExpulsar);
					}
					break;
					
				case PAUSA_ALUMNO_BANO:
					
					// Pido el alumno que quiere ir al bano y realizo la accion del profesor
					Map.Entry<Alumno, Thread> alumnoBano = pedirAlumno(scanner, hilosAlumno);
					yoMismo.dejarAlumnoBano(alumnoBano.getKey());
					break;
					
				case INFO_EXAMEN:
					
					System.out.println(yoMismo.recuperoInformacionExamenEnCurso(hilosAlumno));
					break;
					
				case FINALIZAR_EXAMEN:
					
					yoMismo.finalizoExamen(hilosAlumno.keySet());
					break;
					
				default:
					
					System.err.println("No me has dado una opcion valida");
					
			}	
			
		}
		
	}

	private static Entry<Alumno, Thread> pedirAlumno(Scanner scanner, Map<Alumno, Thread> hilosAlumno) {
		
		// Enseñamos la lista de Alumnos.
		System.out.println("Lista de alumnos: ");
		for (Alumno alumno: hilosAlumno.keySet()) {
			System.out.println("Alumno: "+alumno.getNombre());
		}
		
		//Pedimos el nombre del alumno
		System.out.print("Dime el alumno: ");
		String alumno = scanner.nextLine();
		
		// Encontramos en el mapa por alumnos
		for (Map.Entry<Alumno, Thread> infoAlumno: hilosAlumno.entrySet()) {
			if (infoAlumno.getKey().getNombre().equals(alumno)) {
				return infoAlumno;
			}
		}
		
		return null;
	}


	/**
	 * Pedir una pregunta
	 * @param scanner
	 * @return
	 */
	private static Pregunta pedirPreguntaNueva(Scanner scanner) {
		
		//Pedimos el enunciado
		System.out.println("Dime un enunciado para la pregunta nueva: ");
		String enunciado = scanner.nextLine();
		
		// Pedimos el tipo de pregunta
		String menuTipos = TipoPregunta.montarMenuTipos();
		System.out.println(menuTipos);
		System.out.print("Dime el indice: ");
		String tipoPreguntaIndice = scanner.nextLine();
		TipoPregunta tipoPregunta = TipoPregunta.NO_TYPE;
		if (tipoPreguntaIndice.isBlank() || tipoPreguntaIndice.isEmpty()) {
			System.out.println("Como no me lo dices le pondremos Verdadero o Falso");
			tipoPregunta = TipoPregunta.VERDADERO_FALSO;
		}
		else {
			tipoPregunta = TipoPregunta.getTipoDeIndice(tipoPreguntaIndice);
		}
		
		//Motamos respuesta
		Pregunta preguntaNueva = new Pregunta(enunciado, tipoPregunta);
		return preguntaNueva;
		
	}

}
