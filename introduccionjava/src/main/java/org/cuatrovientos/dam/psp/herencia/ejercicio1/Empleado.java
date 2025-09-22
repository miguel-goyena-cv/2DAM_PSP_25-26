package org.cuatrovientos.dam.psp.herencia.ejercicio1;

public class Empleado extends Trabajador{
	
	private float sueldoBruto;
	private float impuestos;
	
	private static final int PAGAS = 14;

	public Empleado(String nombre, String puesto, String direccion, String nSS, float sueldoBruto, float impuestos) {
		super(nombre, puesto, direccion, nSS);
		this.sueldoBruto = sueldoBruto;
		this.impuestos = impuestos;
	}
	
	/**
	 * Calcula la paga mensual, quitando impuesto y teniendo en cuenta el numero de PAGAS al año
	 * @return
	 */
	public float calcularPaga() {
		return (this.sueldoBruto - this.impuestos)/Empleado.PAGAS;
	}

	public float getSueldoBruto() {
		return sueldoBruto;
	}

	public void setSueldoBruto(float sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}

	public float getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(float impuestos) {
		this.impuestos = impuestos;
	}

	public static int getPagas() {
		return PAGAS;
	}

	@Override
	public String toString() {
		return "Empleado [sueldoBruto=" + sueldoBruto + ", impuestos=" + impuestos + ", Inherited()=" + super.toString()
				+ "]";
	}

}
