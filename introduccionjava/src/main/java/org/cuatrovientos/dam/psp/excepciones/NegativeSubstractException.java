package org.cuatrovientos.dam.psp.excepciones;

public class NegativeSubstractException extends Exception {

	private int operando1;
	private int operando2;

	public NegativeSubstractException(int operando1, int operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}

	@Override
	public String getMessage() {
		return "NegativeSubstractException: " + this.operando1 + " - " + this.operando2 + " el  resultado es negativo";
	}

}
