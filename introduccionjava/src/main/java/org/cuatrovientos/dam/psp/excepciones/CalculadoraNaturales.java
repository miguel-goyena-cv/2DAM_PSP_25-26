package org.cuatrovientos.dam.psp.excepciones;

public class CalculadoraNaturales {

	private int operando1;
	private int operando2;

	public CalculadoraNaturales(int operando1, int operando2) {
		super();
		this.operando1 = operando1;
		this.operando2 = operando2;
	}

	public int resta() throws NegativeSubstractException {
		
		// Los operadores deben de ser naturales TODO pasarlo al constructor
		if (operando1 < 0 || operando2 < 0) {
			throw new IllegalArgumentException("Los numeros " + operando1 + ", " + operando2 + " no son naturales");
		}
		
		// El restultado debe de ser un numero natural
		if ((operando1 - operando2) < 0) {
			throw new NegativeSubstractException(operando1, operando2);
		}
		
		return operando1 - operando2;
		
		
		
	}

}
