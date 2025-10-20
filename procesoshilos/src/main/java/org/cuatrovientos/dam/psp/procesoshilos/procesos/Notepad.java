package org.cuatrovientos.dam.psp.procesoshilos.procesos;

public class Notepad {

	public void lanzarNotepad(String fichero) {
		ProcessBuilder pb;
		try {
			if (fichero == "")
				pb = new ProcessBuilder("notepad.exe");
			else
				pb = new ProcessBuilder("notepad.exe", fichero);
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lanzarCalc() {
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("calc.exe");
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
