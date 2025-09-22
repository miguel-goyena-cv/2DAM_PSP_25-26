package org.cuatrovientos.dam.psp.herencia.ejercicio1;

import java.time.Duration;

public class Consultor extends Empleado {
	
	private Duration horas; // VER EL API de DURATION, es muy interesante.
	private float precioHora;
	
	
	public Consultor(String nombre, String puesto, String direccion, String nSS, float sueldoBruto, float impuestos,
			Duration horas, float precioHora) {
		super(nombre, puesto, direccion, nSS, sueldoBruto, impuestos);
		this.horas = horas;
		this.precioHora = precioHora;
	}
	
	public float calcularPaga () {
		return precioHora * horas.toHours();
	}


	public Duration getHoras() {
		return horas;
	}


	public void setHoras(Duration horas) {
		this.horas = horas;
	}


	public float getPrecioHora() {
		return precioHora;
	}


	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}


	@Override
	public String toString() {
		return "Consultor [horas=" + horas + ", precioHora=" + precioHora + ", inherited()=" + super.toString() + "]";
	}
	
	

}
